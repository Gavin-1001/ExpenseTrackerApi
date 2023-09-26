package com.financetracker.Controller;


import com.financetracker.Entity.AuthUser;
import com.financetracker.Services.AuthService.AuthenticationService;
import com.financetracker.Services.JwtRefreshTokenService.JwtRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        @Autowired
        private AuthenticationService authenticationService;

        @Autowired
        private JwtRefreshTokenService jwtRefreshTokenService;

        @PostMapping("/register")
        public ResponseEntity<?> signUp(@RequestBody AuthUser user) throws RuntimeException {
            if (authenticationService.findByUsername(user.getUsername()).isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(authenticationService.saveUser(user), HttpStatus.CREATED);
        }

        @PostMapping("/signin")
        public ResponseEntity<?> signIn(@RequestBody AuthUser user) {
            return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
        }

        @PostMapping("/refreshToken")
        public ResponseEntity<?> refreshToken(@RequestParam String token) {
            return ResponseEntity.ok(jwtRefreshTokenService.generateAccessTokenFromRefreshToken(token));
        }



        @GetMapping("/getAllAuthUsers")
        public ResponseEntity<?> getAllAuthUsers(){
            return ResponseEntity.ok(authenticationService.getAllAuthUsers());
        }


    }

