package com.financetracker.Services.UserServices;

import com.financetracker.Entity.AuthUser;
import com.financetracker.Entity.Role;
import com.financetracker.Entity.User;
import com.financetracker.Repository.UserAuthRepository;
import com.financetracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

        @Autowired
        private UserAuthRepository userAuthRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;



        @Override
        public AuthUser saveUser(AuthUser user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.USER);
            return userAuthRepository.save(user);
        }

        @Override
        public Optional<AuthUser> findByUsername(String username){
            return userAuthRepository.findByUsername(username);
        }

        @Override
        public Optional<User> getUserDetailsById(Long id) {
            return userRepository.findById(id);
        }

}
