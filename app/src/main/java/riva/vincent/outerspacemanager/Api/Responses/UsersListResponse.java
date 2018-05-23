package riva.vincent.outerspacemanager.Api.Responses;

import java.util.ArrayList;

import riva.vincent.outerspacemanager.Api.Responses.Models.User;

/**
 * Created by treast on 23/05/2018.
 */

public class UsersListResponse {
    private ArrayList<User> users;

    public UsersListResponse(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {

        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
