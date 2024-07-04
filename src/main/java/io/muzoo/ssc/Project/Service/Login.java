package io.muzoo.ssc.Project.Service;

import lombok.Getter;

public class Login {
    @Getter
    private final Token accessToken;
    @Getter
    private final Token refreshToken;

    private static final long ACCESS_TOKEN_VALIDITY = 1L;
    private static final long REFRESH_TOKEN_VALIDITY = 1L;
    private Login(Token accessToken, Token refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    public static Login of(Long userId, String accessSecret, String refreshSecret){
        return new Login(
                Token.of(userId,ACCESS_TOKEN_VALIDITY,accessSecret),
                Token.of(userId,REFRESH_TOKEN_VALIDITY,refreshSecret)
        );
    }

    public static Login of(Long userId, String accessSecret, Token refreshToken){
        return new Login(
                Token.of(userId,ACCESS_TOKEN_VALIDITY,accessSecret),
                refreshToken
        );
    }
}
