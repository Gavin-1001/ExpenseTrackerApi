package com.financetracker.Services.JwtRefreshTokenService;


import com.financetracker.Entity.AuthUser;
import com.financetracker.Entity.JwtRefreshToken;
import com.financetracker.Repository.JwtRefreshTokenRepository;
import com.financetracker.Repository.UserAuthRepository;
import com.financetracker.Utils.SecurityUtils;
import com.financetracker.security.UserPrincipal;
import com.financetracker.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

@Service
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService{


    @Value("86400000")
    private Long REFRESH_EXPIRATION_IN_MS;

    @Autowired
    private JwtRefreshTokenRepository jwtRefreshTokenRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public JwtRefreshToken createRefreshToken(String userId) {
        JwtRefreshToken jwtRefreshToken = new JwtRefreshToken();
        jwtRefreshToken.setTokenId(UUID.randomUUID().toString());
        jwtRefreshToken.setUserId(userId);
        jwtRefreshToken.setWhenTokenCreated(LocalDateTime.now());
        jwtRefreshToken.setWhenTokenExpirationDate(LocalDateTime.now().plus(REFRESH_EXPIRATION_IN_MS, ChronoUnit.MILLIS));

        return jwtRefreshTokenRepository.save(jwtRefreshToken);
    }

    @Override
    public AuthUser generateAccessTokenFromRefreshToken(String refreshTokenId){

        JwtRefreshToken jwtRefreshToken = jwtRefreshTokenRepository.findById(refreshTokenId).orElseThrow();

        if(jwtRefreshToken.getWhenTokenExpirationDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("JWT REFRESH TOKEN IS NOT VALID");
        }

        AuthUser user = userAuthRepository.findById(Long.valueOf(jwtRefreshToken.getUserId())).orElseThrow();

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password((user.getPassword()))
                .authorities(Set.of(SecurityUtils.convertToAuthority(user.getRole().name())))
                .build();

        String accessToken = jwtProvider.generateToken(userPrincipal);

        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshTokenId);

        return user;
    }

}
