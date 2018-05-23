package riva.vincent.outerspacemanager.galaxy;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;
import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;
import riva.vincent.outerspacemanager.Api.Responses.Models.User;

/**
 * Created by treast on 27/03/2018.
 */

public interface GalaxyView {

    void showToastFailure();
    void displayUsersInTheListView(ArrayList<User> usersList);
    void showSuccessAttack(String code, double time);

}
