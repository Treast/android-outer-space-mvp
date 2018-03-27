package riva.vincent.outerspacemanager.Building;

/**
 * Created by treast on 27/03/2018.
 */

public interface BuildingPresenter {

    void getBuildings(String token);
    void createBuilding(String token, Integer buildingId);

}
