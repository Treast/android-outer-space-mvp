package riva.vincent.outerspacemanager.Shipyard;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;
import riva.vincent.outerspacemanager.Api.Requests.ShipAttackRequest;
import riva.vincent.outerspacemanager.Api.Requests.ShipCreateRequest;
import riva.vincent.outerspacemanager.Api.Responses.BuildingCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.BuildingListResponse;
import riva.vincent.outerspacemanager.Api.Responses.FleetListResponse;
import riva.vincent.outerspacemanager.Api.Responses.MyShipsResponse;
import riva.vincent.outerspacemanager.Api.Responses.ShipAttackResponse;
import riva.vincent.outerspacemanager.Api.Responses.ShipCreateResponse;

/**
 * Created by treast on 27/03/2018.
 */

public class ShipyardPresenterImpl implements ShipyardPresenter {

    private ShipyardView buildingView;

    public ShipyardPresenterImpl(ShipyardView buildingView) {
        this.buildingView = buildingView;
    }

    @Override
    public void getFleet(String token) {
        Call<MyShipsResponse> buildingList = Api.getInstance().getShipList(token);

        buildingList.enqueue(new Callback<MyShipsResponse>() {
            @Override
            public void onResponse(Call<MyShipsResponse> call, Response<MyShipsResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                Log.d("OuterSpace", "onResponse: " + response.body().getShips());
                if(response.body() != null) {
                    Log.d("OuterSpace", "onResponse: There is buildings " + response.body().getShips().size());
                    buildingView.displayFleetInTheListView(response.body().getShips());
                }
            }

            @Override
            public void onFailure(Call<MyShipsResponse> call, Throwable t) {
                buildingView.showToastFailure();
            }
        });
    }

    @Override
    public void createShip(String token, Integer shipId, Integer amount) {

        Call<ShipCreateResponse> buildingCreate = Api.getInstance().shipCreate(token, shipId, new ShipCreateRequest(shipId, amount));

        buildingCreate.enqueue(new Callback<ShipCreateResponse>() {
            @Override
            public void onResponse(Call<ShipCreateResponse> call, Response<ShipCreateResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                if(response.body() != null) {

                }
            }

            @Override
            public void onFailure(Call<ShipCreateResponse> call, Throwable t) {
                buildingView.showToastFailure();
            }
        });
    }

    @Override
    public void attackUser(String token, String username, List<ShipAmount> ships) {
        Call<ShipAttackResponse> shipAttack = Api.getInstance().shipAttack(token, username, new ShipAttackRequest(ships));

        shipAttack.enqueue(new Callback<ShipAttackResponse>() {
            @Override
            public void onResponse(Call<ShipAttackResponse> call, Response<ShipAttackResponse> response) {
                if(response.body() != null) {
                    //buildingView.displayFleetInTheListView(response.body().getShips());
                    buildingView.showSuccessAttack(response.body().getCode(), response.body().getAttackTime());
                }
                Log.d("OuterSpace", "onResponse: response null ");
            }

            @Override
            public void onFailure(Call<ShipAttackResponse> call, Throwable t) {
                buildingView.showToastFailure();
            }
        });
    }
}
