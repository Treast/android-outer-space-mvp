package riva.vincent.outerspacemanager.Fleet;
/**
 * Created by treast on 27/03/2018.
 */

public interface FleetPresenter {

    void getFleet(String token);
    void createShip(String token, Integer shipId);

}
