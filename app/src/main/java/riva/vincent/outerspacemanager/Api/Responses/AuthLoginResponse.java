package riva.vincent.outerspacemanager.Api.Responses;

/**
 * Created by vriva on 26/03/2018.
 */

public class AuthLoginResponse {
    private String token;
    private double expires;

    public AuthLoginResponse(String token, double expires) {
        this.token = token;
        this.expires = expires;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getExpires() {
        return expires;
    }

    public void setExpires(double expires) {
        this.expires = expires;
    }
}
