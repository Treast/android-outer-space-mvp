package riva.vincent.outerspacemanager.galaxy;

import java.util.ArrayList;

import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;
import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;

/**
 * Created by treast on 27/03/2018.
 */

public interface GalaxyPresenter {
    void getUsers(String token);
    void attackUser(String token, String username);
}
