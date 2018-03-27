package riva.vincent.outerspacemanager.Api.Responses;

/**
 * Created by treast on 27/03/2018.
 */

public class ShipCreateResponse {
    private String internalCode;

    public ShipCreateResponse(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getInternalCode() {

        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }
}
