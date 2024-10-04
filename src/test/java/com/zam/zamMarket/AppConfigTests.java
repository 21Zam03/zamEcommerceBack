package com.zam.zamMarket;

import com.zam.zamMarket.Enums.RoleEnum;
import com.zam.zamMarket.entity.PermissionEntity;
import com.zam.zamMarket.entity.RoleEntity;
import com.zam.zamMarket.entity.UserEntity;
import com.zam.zamMarket.repository.PermissionRepository;
import com.zam.zamMarket.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.relation.Role;
import java.util.Set;

@SpringBootTest
class AppConfigTests {
/*
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PermissionRepository permissionRepository;
*/
	@Test
	void contextLoads() {
/*
		PermissionEntity createPermission = PermissionEntity.builder()
				.name("CREATE")
				.build();
		PermissionEntity readPermission = PermissionEntity.builder()
				.name("READ")
				.build();
		PermissionEntity updatePermission = PermissionEntity.builder()
				.name("UPDATE")
				.build();
		PermissionEntity deletePermission = PermissionEntity.builder()
				.name("DELETE")
				.build();
		PermissionEntity refactorPermission = PermissionEntity.builder()
				.name("REFACTOR")
				.build();

		permissionRepository.saveAll(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission));


		RoleEntity roleAdmin = RoleEntity.builder()
				.roleEnum(RoleEnum.ADMIN)
				.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
				.build();

		RoleEntity roleUser = RoleEntity.builder()
				.roleEnum(RoleEnum.CLIENT)
				.permissionList(Set.of(createPermission, readPermission))
				.build();

		RoleEntity roleInvited = RoleEntity.builder()
				.roleEnum(RoleEnum.INVITED)
				.permissionList(Set.of(readPermission))
				.build();

		RoleEntity roleDeveloper = RoleEntity.builder()
				.roleEnum(RoleEnum.DEVELOPER)
				.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
				.build();
*/
	}

}
