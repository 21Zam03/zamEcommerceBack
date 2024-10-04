package com.zam.zamMarket.service;

import com.zam.zamMarket.payload.request.SignInRequest;
import com.zam.zamMarket.payload.request.SignUpRequest;
import com.zam.zamMarket.payload.response.SignInResponse;
import com.zam.zamMarket.payload.response.SignUpResponse;

public interface AuthService {

    public SignUpResponse signUp(SignUpRequest signUpRequest);
    public SignInResponse signIn(SignInRequest signInRequest);

}
