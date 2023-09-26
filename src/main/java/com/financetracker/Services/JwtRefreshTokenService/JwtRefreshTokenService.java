package com.financetracker.Services.JwtRefreshTokenService;


import com.financetracker.Entity.AuthUser;
import com.financetracker.Entity.JwtRefreshToken;

public interface JwtRefreshTokenService {

    JwtRefreshToken createRefreshToken(String userId);

    AuthUser generateAccessTokenFromRefreshToken(String refreshTokenId);
}
