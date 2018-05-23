package riva.vincent.outerspacemanager.galaxy;

import java.util.ArrayList;

import riva.vincent.outerspacemanager.Api.Responses.Models.User;

/**
 * Created by treast on 27/03/2018.
 */

public interface GalaxyView {

    void showToastFailure();
    void displayUsersInTheListView(ArrayList<User> usersList);

}
