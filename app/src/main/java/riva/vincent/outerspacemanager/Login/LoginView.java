package riva.vincent.outerspacemanager.Login;

/**
 * Created by treast on 27/03/2018.
 */

public interface LoginView {

    void showMainActivity();
    void setToken(String token, double expires);
    void showToastFailure();

}
