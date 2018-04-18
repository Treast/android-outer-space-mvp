package riva.vincent.outerspacemanager.Shipyard;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Fleet;
import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;

/**
 * Created by treast on 27/03/2018.
 */

public interface ShipyardView {
    void showToastFailure();
    void showSuccessAttack(String code, double time);

    void displayFleetInTheListView(List<Ship> buildings);
}
