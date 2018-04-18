package riva.vincent.outerspacemanager.Api.Requests;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;

/**
 * Created by vriva on 18/04/2018.
 */

public class ShipAttackRequest {
    private List<ShipAmount> ships;

    public ShipAttackRequest(List<ShipAmount> ships) {
        this.ships = ships;
    }

    public List<ShipAmount> getShips() {
        return ships;
    }

    public void setShips(List<ShipAmount> ships) {
        this.ships = ships;
    }
}
