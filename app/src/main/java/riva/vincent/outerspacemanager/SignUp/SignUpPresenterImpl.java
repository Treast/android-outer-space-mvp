package riva.vincent.outerspacemanager.SignUp;

import android.content.SharedPreferences;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.AuthCreateRequest;
import riva.vincent.outerspacemanager.Api.Responses.AuthCreateResponse;

/**
 * Created by treast on 27/03/2018.
 */

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpView signUpView;

    public SignUpPresenterImpl(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void signUp(String username, String password, String email) {
        Call<AuthCreateResponse> authCreate = Api.getInstance().authCreate(new AuthCreateRequest(username, password, email));

        authCreate.enqueue(new Callback<AuthCreateResponse>() {
            @Override
            public void onResponse(Call<AuthCreateResponse> call, Response<AuthCreateResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response.body().toString());
                if(response.body() != null) {
                    signUpView.setToken(response.body().getToken(), response.body().getExpires());
                    signUpView.showMainActivity();
                }
            }

            @Override
            public void onFailure(Call<AuthCreateResponse> call, Throwable t) {
                signUpView.showToastFailure();
            }
        });
    }

}
