package riva.vincent.outerspacemanager.Shipyard;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;

/**
 * Created by treast on 27/03/2018.
 */

public interface ShipyardPresenter {

    void getFleet(String token);
    void createShip(String token, Integer shipId);
    void attackUser(String token, String username, List<ShipAmount> ships);

}
