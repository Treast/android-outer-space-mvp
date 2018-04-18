package riva.vincent.outerspacemanager.Main;

/**
 * Created by treast on 27/03/2018.
 */

public interface MainView {
    void updateCurrentUserInformations(String username, Long points, float gas, float minerals, float gasModifier, float mineralsModifier);

    void showToastFailure();
}
