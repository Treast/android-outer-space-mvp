package riva.vincent.outerspacemanager.report;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Report;
import riva.vincent.outerspacemanager.Api.Responses.Models.Search;

/**
 * Created by vriva on 18/04/2018.
 */

public interface ReportView {
    void showToastFailure();

    void displaySearchesInTheListView(List<Report> searches);
}
