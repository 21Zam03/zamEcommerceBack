package com.zam.zamMarket.service.impl;

import com.zam.zamMarket.Enums.GenreEnum;
import com.zam.zamMarket.Enums.IdTypeEnum;
import com.zam.zamMarket.entity.ClientEntity;
import com.zam.zamMarket.entity.RoleEntity;
import com.zam.zamMarket.entity.UserEntity;
import com.zam.zamMarket.exceptions.DuplicateException;
import com.zam.zamMarket.exceptions.NotFoundException;
import com.zam.zamMarket.payload.request.SignInRequest;
import com.zam.zamMarket.payload.request.SignUpRequest;
import com.zam.zamMarket.payload.response.SignInResponse;
import com.zam.zamMarket.payload.response.SignUpResponse;
import com.zam.zamMarket.repository.ClientRepository;
import com.zam.zamMarket.repository.RoleRepository;
import com.zam.zamMarket.repository.UserRepository;
import com.zam.zamMarket.service.AuthService;
import com.zam.zamMarket.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthServiceImpl(ClientRepository clientRepository, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils,
                           UserDetailsService userDetailsService) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        boolean isDuplicate = clientRepository.existsByIdentityDocumentNumber(signUpRequest.getIdentityDocumentNumber());

        if (isDuplicate) {
            throw new DuplicateException
                    ("Identity document number "+ signUpRequest.getIdentityDocumentNumber() +
                            " is already registered in the system");
        }

        isDuplicate = clientRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber());
        if (isDuplicate) {
            throw new DuplicateException("Phone number "+ signUpRequest.getPhoneNumber() +
                    " is already registered in the system");
        }

        Set<RoleEntity> roleList = new HashSet<>();
        signUpRequest.getRoleList().forEach(roleId -> {
            RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() ->
                    new NotFoundException("Role with id "+roleId+" was not fond" ));
            roleList.add(roleEntity);
        });

        isDuplicate = userRepository.existsByEmail(signUpRequest.getEmail());

        if (isDuplicate) {
            throw new DuplicateException("Email "+ signUpRequest.getEmail() + " is already registered in the system");
        }

        UserEntity user = UserEntity.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .roleList(roleList)
                .build();
        UserEntity userCreated = userRepository.save(user);

        ClientEntity client = ClientEntity.builder()
                .user(userCreated)
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .typeIdentityDocument(IdTypeEnum.valueOf(signUpRequest.getTypeIdentityDocument()))
                .identityDocumentNumber(signUpRequest.getIdentityDocumentNumber())
                .phoneCountryCode(signUpRequest.getPhoneCountryCode())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .country(signUpRequest.getCountry())
                .genre(GenreEnum.valueOf(signUpRequest.getGenre()))
                .birthdate(signUpRequest.getBirthdate())
                .isActive(true)
                .build();
        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        clientRepository.save(client);

        userCreated.getRoleList().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));

        userCreated.getRoleList()
                .stream()
                .flatMap(roleEntity -> roleEntity.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getEmail(), userCreated.getPassword(), authorityList);
        String accessToken = jwtUtils.createToken(authentication);

        log.info("Client was registered successfully, email: {} ", signUpRequest.getEmail());
        return SignUpResponse.builder()
                .email(userCreated.getEmail())
                .message("Client was registered successfully")
                .token(accessToken)
                .status(200)
                .build();
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        return SignInResponse.builder()
                .email(email)
                .message("User logged successfully")
                .token(accessToken)
                .status(200)
                .build();
    }

    public Authentication authenticate (String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid email or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }

}
