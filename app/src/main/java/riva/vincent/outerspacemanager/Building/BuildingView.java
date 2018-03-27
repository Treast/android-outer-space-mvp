package riva.vincent.outerspacemanager.Building;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;

/**
 * Created by treast on 27/03/2018.
 */

public interface BuildingView {
    void showToastFailure();

    void displayBuildingInTheListView(List<Building> buildings);
}
