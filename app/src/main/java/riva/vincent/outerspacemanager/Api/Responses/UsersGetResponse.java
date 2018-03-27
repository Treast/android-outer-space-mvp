package riva.vincent.outerspacemanager.Api.Responses;

/**
 * Created by treast on 27/03/2018.
 */

public class UsersGetResponse {
    private float gas;
    private Integer gasModifier;
    private float minerals;
    private Integer mineralsModifier;
    private Long points;
    private String username;

    public UsersGetResponse(float gas, Integer gasModifier, float minerals, Integer mineralsModifier, Long points, String username) {
        this.gas = gas;
        this.gasModifier = gasModifier;
        this.minerals = minerals;
        this.mineralsModifier = mineralsModifier;
        this.points = points;
        this.username = username;
    }

    public float getGas() {
        return gas;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }

    public Integer getGasModifier() {
        return gasModifier;
    }

    public void setGasModifier(Integer gasModifier) {
        this.gasModifier = gasModifier;
    }

    public float getMinerals() {
        return minerals;
    }

    public void setMinerals(float minerals) {
        this.minerals = minerals;
    }

    public Integer getMineralsModifier() {
        return mineralsModifier;
    }

    public void setMineralsModifier(Integer mineralsModifier) {
        this.mineralsModifier = mineralsModifier;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
