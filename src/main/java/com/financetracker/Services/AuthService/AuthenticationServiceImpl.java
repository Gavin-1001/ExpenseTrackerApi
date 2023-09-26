package com.financetracker.Services.AuthService;

import com.financetracker.Entity.AuthUser;
import com.financetracker.Entity.Role;
import com.financetracker.Entity.User;
import com.financetracker.Repository.UserAuthRepository;
import com.financetracker.Services.JwtRefreshTokenService.JwtRefreshTokenService;
import com.financetracker.security.UserPrincipal;
import com.financetracker.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JwtRefreshTokenService jwtRefreshTokenService;


    @Override
    public AuthUser signInAndReturnJWT(AuthUser signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        AuthUser signInUser = userPrincipal.getUser();
        signInUser.setAccessToken(jwt);
        signInUser.setRefreshToken(jwtRefreshTokenService.createRefreshToken(signInUser.getId()).getTokenId());

        return signInUser;
    }

    @Override
    public Optional<AuthUser> findByUsername(String username) {
        return userAuthRepository.findByUsername(username);
    }




    @Override
    public List<AuthUser> getAllAuthUsers() {
        return null;
    }

    @Override
    public AuthUser saveUser(AuthUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //if statement for user or employer was here
        user.setRole(Role.USER);
        //getFirstName from authUser and set to User
        User newUser = new User();
        newUser.setFirstName(user.getUsername());
        return userAuthRepository.save(user);

    }
}
