package org.example.springtask1.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String username;
    private String accessToken;
    private String refreshToken;

    public LoginResponse(String username, String accessToken, String refreshToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    public LoginResponse() {
    }

    public static class LoginResponseBuilder {
        private String username;
        private String accessToken;
        private String refreshToken;

        public LoginResponseBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public LoginResponseBuilder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public LoginResponseBuilder withRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public LoginResponse build() {
            return new LoginResponse(username, accessToken, refreshToken);
        }
    }

    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
