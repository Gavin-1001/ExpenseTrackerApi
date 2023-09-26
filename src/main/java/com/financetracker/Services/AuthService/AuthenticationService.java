package com.financetracker.Services.AuthService;

import com.financetracker.Entity.AuthUser;

import java.util.List;
import java.util.Optional;

public interface AuthenticationService {

    AuthUser signInAndReturnJWT(AuthUser signInRequest);

    AuthUser saveUser(AuthUser user);

    Optional<AuthUser> findByUsername(String username);


    List<AuthUser> getAllAuthUsers();
}
