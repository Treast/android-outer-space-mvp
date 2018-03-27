package riva.vincent.outerspacemanager.Api.Responses;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;

/**
 * Created by treast on 27/03/2018.
 */

public class MyShipsResponse {
    private Integer size;
    private List<Ship> ships;

    public MyShipsResponse(Integer size, List<Ship> ships) {
        this.size = size;
        this.ships = ships;
    }

    public Integer getSize() {

        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
