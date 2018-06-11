package riva.vincent.outerspacemanager.Api.Responses;

import java.util.ArrayList;

import riva.vincent.outerspacemanager.Api.Responses.Models.Report;

/**
 * Created by treast on 11/06/2018.
 */

public class ReportGetResponse {
    private ArrayList<Report> reports;

    public ReportGetResponse(ArrayList<Report> reports) {
        this.reports = reports;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }
}
