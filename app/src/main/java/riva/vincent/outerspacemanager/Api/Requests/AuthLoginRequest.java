package riva.vincent.outerspacemanager.Api.Requests;

/**
 * Created by vriva on 26/03/2018.
 */

public class AuthLoginRequest {
    private String username;
    private String password;

    public AuthLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
