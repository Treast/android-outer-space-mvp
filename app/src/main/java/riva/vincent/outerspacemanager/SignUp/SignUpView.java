package riva.vincent.outerspacemanager.SignUp;

/**
 * Created by treast on 27/03/2018.
 */

public interface SignUpView {

    void showToastFailure();
    void showMainActivity();
    void setToken(String token, double expires);

}
