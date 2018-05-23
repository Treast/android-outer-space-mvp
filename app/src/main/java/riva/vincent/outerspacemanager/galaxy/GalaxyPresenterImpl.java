package riva.vincent.outerspacemanager.galaxy;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.AuthLoginRequest;
import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;
import riva.vincent.outerspacemanager.Api.Requests.ShipAttackRequest;
import riva.vincent.outerspacemanager.Api.Responses.AuthLoginResponse;
import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;
import riva.vincent.outerspacemanager.Api.Responses.MyShipsResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchListResponse;
import riva.vincent.outerspacemanager.Api.Responses.ShipAttackResponse;
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

    @Override
    public void attackUser(final String token, final String username) {
        Log.d("OuterSpace", "attackUser: start");
        Call<MyShipsResponse> buildingList = Api.getInstance().getShipList(token);

        buildingList.enqueue(new Callback<MyShipsResponse>() {
            @Override
            public void onResponse(Call<MyShipsResponse> call, Response<MyShipsResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                Log.d("OuterSpace", "onResponse: " + response.body().getShips());
                if(response.body() != null) {
                    ArrayList<ShipAmount> ships = new ArrayList<>();
                    for(int i = 0; i < response.body().getShips().size(); i++) {
                        Log.d("OuterSpace", "i: " + i  + " and max: " + response.body().getShips().size());
                        Ship ship = response.body().getShips().get(i);
                        ships.add(new ShipAmount(ship.getShipId(), ship.getAmount()));
                    }
                    Call<ShipAttackResponse> shipAttack = Api.getInstance().shipAttack(token, username, new ShipAttackRequest(ships));

                    shipAttack.enqueue(new Callback<ShipAttackResponse>() {
                        @Override
                        public void onResponse(Call<ShipAttackResponse> call, Response<ShipAttackResponse> response) {
                            if(response.body() != null) {
                                //buildingView.displayFleetInTheListView(response.body().getShips());
                                galaxyView.showSuccessAttack(response.body().getCode(), response.body().getAttackTime());
                            }
                            Log.d("OuterSpace", "onResponse: response null ");
                        }

                        @Override
                        public void onFailure(Call<ShipAttackResponse> call, Throwable t) {
                            galaxyView.showToastFailure();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MyShipsResponse> call, Throwable t) {
                galaxyView.showToastFailure();
            }
        });
    }
}
