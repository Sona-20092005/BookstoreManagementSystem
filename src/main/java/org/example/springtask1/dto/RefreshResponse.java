package org.example.springtask1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshResponse {

    private String username;
    private String accessToken;
    private String refreshToken;

    public RefreshResponse(String username, String accessToken, String refreshToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    public RefreshResponse() {
    }

    public static class RefreshResponseBuilder {
        private String username;
        private String accessToken;
        private String refreshToken;

        public RefreshResponseBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public RefreshResponseBuilder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public RefreshResponseBuilder withRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public RefreshResponse build() {
            return new RefreshResponse(username, accessToken, refreshToken);
        }
    }

    public static RefreshResponseBuilder builder() {
        return new RefreshResponseBuilder();
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
