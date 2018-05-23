package riva.vincent.outerspacemanager.Api.Responses.Models;

/**
 * Created by treast on 23/05/2018.
 */

public class User {
    private double points;
    private String username;

    public User(double points, String username) {
        this.points = points;
        this.username = username;
    }

    public double getPoints() {

        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
