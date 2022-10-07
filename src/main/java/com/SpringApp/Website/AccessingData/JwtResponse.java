package com.SpringApp.Website.AccessingData;

public class JwtResponse {
    private User user;
    private String token;

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public JwtResponse(User user, String token)
    {
        this.user=user;
        this.token=token;
    }
}
