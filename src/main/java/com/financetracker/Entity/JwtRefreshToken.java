package com.financetracker.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class JwtRefreshToken {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_token_date")
    private LocalDateTime whenTokenCreated;

    @Column(name = "expiration_date")
    private LocalDateTime whenTokenExpirationDate;

}





