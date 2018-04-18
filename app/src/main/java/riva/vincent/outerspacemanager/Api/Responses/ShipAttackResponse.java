package riva.vincent.outerspacemanager.Api.Responses;

/**
 * Created by vriva on 18/04/2018.
 */

public class ShipAttackResponse {
    private String code;
    private double attackTime;

    public ShipAttackResponse(String code, double attackTime) {
        this.code = code;
        this.attackTime = attackTime;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(double attackTime) {
        this.attackTime = attackTime;
    }
}
