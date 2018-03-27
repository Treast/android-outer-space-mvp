package riva.vincent.outerspacemanager.Api.Responses;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Api.Responses.Models.Fleet;

/**
 * Created by treast on 27/03/2018.
 */

public class FleetListResponse {
    private Integer size;
    private List<Fleet> ships;

    public FleetListResponse(Integer size, List<Fleet> ships) {
        this.size = size;
        this.ships = ships;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Fleet> getShips() {
        return ships;
    }

    public void setShips(List<Fleet> ships) {
        this.ships = ships;
    }
}
