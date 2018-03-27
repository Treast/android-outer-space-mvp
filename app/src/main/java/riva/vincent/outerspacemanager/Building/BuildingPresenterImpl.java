package riva.vincent.outerspacemanager.Building;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.BuildingCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.BuildingListResponse;

/**
 * Created by treast on 27/03/2018.
 */

public class BuildingPresenterImpl implements BuildingPresenter {

    private BuildingView buildingView;

    public BuildingPresenterImpl(BuildingView buildingView) {
        this.buildingView = buildingView;
    }

    @Override
    public void getBuildings(String token) {
        Call<BuildingListResponse> buildingList = Api.getInstance().getBuildingList(token);

        buildingList.enqueue(new Callback<BuildingListResponse>() {
            @Override
            public void onResponse(Call<BuildingListResponse> call, Response<BuildingListResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                if(response.body() != null) {
                    Log.d("OuterSpace", "onResponse: There is buildings " + response.body().getBuildings().size());
                    buildingView.displayBuildingInTheListView(response.body().getBuildings());
                }
            }

            @Override
            public void onFailure(Call<BuildingListResponse> call, Throwable t) {
                buildingView.showToastFailure();
            }
        });
    }

    @Override
    public void createBuilding(String token, Integer buildingId) {
        Call<BuildingCreateResponse> buildingCreate = Api.getInstance().buildingCreate(token, buildingId);

        buildingCreate.enqueue(new Callback<BuildingCreateResponse>() {
            @Override
            public void onResponse(Call<BuildingCreateResponse> call, Response<BuildingCreateResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                if(response.body() != null) {

                }
            }

            @Override
            public void onFailure(Call<BuildingCreateResponse> call, Throwable t) {
                buildingView.showToastFailure();
            }
        });
    }
}
