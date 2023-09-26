package com.financetracker.Services.UserServices;

import com.financetracker.Entity.AuthUser;
import com.financetracker.Entity.User;

import java.util.Optional;

public interface UserService {
    AuthUser saveUser(AuthUser user);

    Optional<AuthUser> findByUsername(String username);


    Optional<User> getUserDetailsById(Long id);
}
