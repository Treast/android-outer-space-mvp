package riva.vincent.outerspacemanager.Api.Responses.Models;

import java.util.ArrayList;

import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;

/**
 * Created by treast on 11/06/2018.
 */

public class Report {
    private ArrayList<Ship> attackerFleet;
    private double date;
    private double dateInv;
    private ArrayList<Ship> defenderFleet;
    private String from;
    private double gasWon;
    private double mineralsWon;
    private String to;
    private String type;

    public Report(ArrayList<Ship> attackerFleet, double date, double dateInv, ArrayList<Ship> defenderFleet, String from, double gasWon, double mineralsWon, String to, String type) {
        this.attackerFleet = attackerFleet;
        this.date = date;
        this.dateInv = dateInv;
        this.defenderFleet = defenderFleet;
        this.from = from;
        this.gasWon = gasWon;
        this.mineralsWon = mineralsWon;
        this.to = to;
        this.type = type;
    }

    public ArrayList<Ship> getAttackerFleet() {
        return attackerFleet;
    }

    public void setAttackerFleet(ArrayList<Ship> attackerFleet) {
        this.attackerFleet = attackerFleet;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public double getDateInv() {
        return dateInv;
    }

    public void setDateInv(double dateInv) {
        this.dateInv = dateInv;
    }

    public ArrayList<Ship> getDefenderFleet() {
        return defenderFleet;
    }

    public void setDefenderFleet(ArrayList<Ship> defenderFleet) {
        this.defenderFleet = defenderFleet;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public double getGasWon() {
        return gasWon;
    }

    public void setGasWon(double gasWon) {
        this.gasWon = gasWon;
    }

    public double getMineralsWon() {
        return mineralsWon;
    }

    public void setMineralsWon(double mineralsWon) {
        this.mineralsWon = mineralsWon;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
