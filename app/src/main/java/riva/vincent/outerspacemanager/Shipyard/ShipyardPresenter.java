package riva.vincent.outerspacemanager.Shipyard;
/**
 * Created by treast on 27/03/2018.
 */

public interface ShipyardPresenter {

    void getFleet(String token);
    void createShip(String token, Integer shipId);

}
