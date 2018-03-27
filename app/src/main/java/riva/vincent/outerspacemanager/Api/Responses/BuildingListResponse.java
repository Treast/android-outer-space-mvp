package riva.vincent.outerspacemanager.Api.Responses;

import android.os.Build;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;

/**
 * Created by treast on 27/03/2018.
 */

public class BuildingListResponse {
    private Integer size;
    private List<Building> buildings;

    public BuildingListResponse(Integer size, List<Building> buildings) {
        this.size = size;
        this.buildings = buildings;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
