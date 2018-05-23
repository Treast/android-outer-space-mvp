package riva.vincent.outerspacemanager.galaxy;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.AuthLoginRequest;
import riva.vincent.outerspacemanager.Api.Responses.AuthLoginResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchListResponse;
import riva.vincent.outerspacemanager.Api.Responses.UsersListResponse;

/**
 * Created by treast on 27/03/2018.
 */

public class GalaxyPresenterImpl implements GalaxyPresenter {

    private GalaxyView galaxyView;

    public GalaxyPresenterImpl(GalaxyView loginView) {
        this.galaxyView = loginView;
    }


    @Override
    public void getUsers(String token) {
        Call<UsersListResponse> usersList = Api.getInstance().getListUsers(token);

        usersList.enqueue(new Callback<UsersListResponse>() {
            @Override
            public void onResponse(Call<UsersListResponse> call, Response<UsersListResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                if(response.body() != null) {
                    Log.d("OuterSpace", "UserListSize: " + response.body().getUsers().size());
                    galaxyView.displayUsersInTheListView(response.body().getUsers());
                }
            }

            @Override
            public void onFailure(Call<UsersListResponse> call, Throwable t) {
                galaxyView.showToastFailure();
            }
        });
    }
}
