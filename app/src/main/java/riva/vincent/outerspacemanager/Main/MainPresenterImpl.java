package riva.vincent.outerspacemanager.Main;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.UsersGetResponse;

/**
 * Created by treast on 27/03/2018.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void getCurrentUser(final String token) {
        Call<UsersGetResponse> currentUser = Api.getInstance().getCurrentUser(token);

        currentUser.enqueue(new Callback<UsersGetResponse>() {
            @Override
            public void onResponse(Call<UsersGetResponse> call, Response<UsersGetResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                Log.d("OuterSpace", "currentToken: " + token);
                if(response.body() != null) {
                    mainView.updateCurrentUserInformations(response.body().getUsername(), response.body().getPoints(), response.body().getGas(), response.body().getMinerals(), response.body().getGasModifier(), response.body().getMineralsModifier());
                }
            }

            @Override
            public void onFailure(Call<UsersGetResponse> call, Throwable t) {
                Log.d("OuterSpace", "onFailure: " + t);
                mainView.showToastFailure();
            }
        });
    }
}
