package riva.vincent.outerspacemanager.Api.Requests;

/**
 * Created by vriva on 18/04/2018.
 */

public class DeviceAddRequest {
    private String deviceToken;

    public DeviceAddRequest(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceToken() {

        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
