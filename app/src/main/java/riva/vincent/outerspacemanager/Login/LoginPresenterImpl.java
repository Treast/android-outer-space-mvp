package riva.vincent.outerspacemanager.Login;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.AuthLoginRequest;
import riva.vincent.outerspacemanager.Api.Responses.AuthLoginResponse;

/**
 * Created by treast on 27/03/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void logIn(String username, String password) {
        Call<AuthLoginResponse> authLogin = Api.getInstance().authLogin(new AuthLoginRequest(username, password));

        authLogin.enqueue(new Callback<AuthLoginResponse>() {
            @Override
            public void onResponse(Call<AuthLoginResponse> call, Response<AuthLoginResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response.body().toString());
                if(response.body() != null) {
                    loginView.setToken(response.body().getToken(), response.body().getExpires());
                    loginView.showMainActivity();
                }
            }

            @Override
            public void onFailure(Call<AuthLoginResponse> call, Throwable t) {
                loginView.showToastFailure();
            }
        });
    }
}
