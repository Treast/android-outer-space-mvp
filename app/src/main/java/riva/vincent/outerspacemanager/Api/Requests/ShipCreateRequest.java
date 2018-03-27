package riva.vincent.outerspacemanager.Api.Requests;

/**
 * Created by treast on 27/03/2018.
 */

public class ShipCreateRequest {
    private Integer shipId;
    private Integer amount;

    public ShipCreateRequest(Integer shipId, Integer amount) {
        this.shipId = shipId;
        this.amount = amount;
    }

    public Integer getShipId() {

        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
