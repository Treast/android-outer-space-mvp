package riva.vincent.outerspacemanager.Login;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.AuthLoginRequest;
import riva.vincent.outerspacemanager.Api.Requests.DeviceAddRequest;
import riva.vincent.outerspacemanager.Api.Responses.AuthLoginResponse;
import riva.vincent.outerspacemanager.Api.Responses.DeviceAddResponse;

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
                final Response<AuthLoginResponse> responseFinal = response;
                if(response.body() != null) {

                    Call<DeviceAddResponse> deviceAdd = Api.getInstance().deviceAdd(response.body().getToken(), new DeviceAddRequest(FirebaseInstanceId.getInstance().getToken()));
                    Log.d("OuterSpace", "currentDeviceToken: " + FirebaseInstanceId.getInstance().getToken());
                    deviceAdd.enqueue(new Callback<DeviceAddResponse>() {
                        @Override
                        public void onResponse(Call<DeviceAddResponse> call, Response<DeviceAddResponse> response2) {
                            loginView.setToken(responseFinal.body().getToken(), responseFinal.body().getExpires());
                            loginView.showMainActivity();
                        }

                        @Override
                        public void onFailure(Call<DeviceAddResponse> call, Throwable t) {
                            loginView.showToastFailure();
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<AuthLoginResponse> call, Throwable t) {
                loginView.showToastFailure();
            }
        });
    }
}
