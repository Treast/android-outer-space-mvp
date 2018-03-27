package riva.vincent.outerspacemanager.Api.Responses;

/**
 * Created by treast on 27/03/2018.
 */

public class BuildingCreateResponse {
    private String code;

    public BuildingCreateResponse(String code) {
        this.code = code;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
