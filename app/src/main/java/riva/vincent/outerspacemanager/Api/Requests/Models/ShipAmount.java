package riva.vincent.outerspacemanager.Api.Requests.Models;

/**
 * Created by vriva on 18/04/2018.
 */

public class ShipAmount {
    private int shipId;
    private int amount;

    public ShipAmount(int shipId, int amount) {
        this.shipId = shipId;
        this.amount = amount;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
