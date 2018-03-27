package riva.vincent.outerspacemanager.Fleet;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.ShipCreateRequest;
import riva.vincent.outerspacemanager.Api.Responses.BuildingCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.BuildingListResponse;
import riva.vincent.outerspacemanager.Api.Responses.FleetListResponse;
import riva.vincent.outerspacemanager.Api.Responses.ShipCreateResponse;

/**
 * Created by treast on 27/03/2018.
 */

public class FleetPresenterImpl implements FleetPresenter {

    private FleetView buildingView;

    public FleetPresenterImpl(FleetView buildingView) {
        this.buildingView = buildingView;
    }

    @Override
    public void getFleet(String token) {
        Call<FleetListResponse> buildingList = Api.getInstance().getFleetList(token);

        buildingList.enqueue(new Callback<FleetListResponse>() {
            @Override
            public void onResponse(Call<FleetListResponse> call, Response<FleetListResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                Log.d("OuterSpace", "onResponse: " + response.body().getShips());
                if(response.body() != null) {
                    Log.d("OuterSpace", "onResponse: There is buildings " + response.body().getShips().size());
                    buildingView.displayFleetInTheListView(response.body().getShips());
                }
            }

            @Override
            public void onFailure(Call<FleetListResponse> call, Throwable t) {
                buildingView.showToastFailure();
            }
        });
    }

    @Override
    public void createShip(String token, Integer shipId) {

        Call<ShipCreateResponse> buildingCreate = Api.getInstance().shipCreate(token, shipId, new ShipCreateRequest(shipId, 1));

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
}
