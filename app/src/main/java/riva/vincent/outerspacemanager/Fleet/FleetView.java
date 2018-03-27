package riva.vincent.outerspacemanager.Fleet;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Fleet;

/**
 * Created by treast on 27/03/2018.
 */

public interface FleetView {
    void showToastFailure();

    void displayFleetInTheListView(List<Fleet> buildings);
}
