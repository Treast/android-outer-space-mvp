package riva.vincent.outerspacemanager.Api.Requests;

/**
 * Created by vriva on 26/03/2018.
 */

public class AuthCreateRequest {
    private String username;
    private String password;
    private String email;

    public AuthCreateRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
