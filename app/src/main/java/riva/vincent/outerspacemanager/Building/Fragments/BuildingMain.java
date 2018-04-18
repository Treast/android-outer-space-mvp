package riva.vincent.outerspacemanager.Building.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Building.BuildingListAdapter;
import riva.vincent.outerspacemanager.Building.BuildingPresenter;
import riva.vincent.outerspacemanager.Building.BuildingPresenterImpl;
import riva.vincent.outerspacemanager.Building.BuildingView;
import riva.vincent.outerspacemanager.R;

/**
 * Created by vriva on 18/04/2018.
 */

public class BuildingMain extends FragmentActivity implements BuildingListAdapter.ClickListener, BuildingView {

    private BuildingPresenter presenter;
    private List<Building> buildingList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_main);
        buildingList = new ArrayList<>();

        presenter = new BuildingPresenterImpl(this);
        presenter.getBuildings(getToken());
    }

    private String getToken() {
        SharedPreferences settings = getSharedPreferences("OuterSpaceManager", 0);
        return settings.getString("token", "");
    }

    @Override
    public void showToastFailure() {
        Api.showFailureToast(this);
    }

    @Override
    public void displayBuildingInTheListView(List<Building> buildings) {
        Log.d("OuterSpace", "DisplayBuilding");
        buildingList = buildings;
        FragmentList fragmentList = (FragmentList)getSupportFragmentManager().findFragmentById(R.id.activity_building_fragment_list);
        if(fragmentList != null && fragmentList.isInLayout()) {
            fragmentList.displayBuildingInTheListView(buildings);
        }
    }

    @Override
    public void onClick(int position, View v) {
        FragmentList fragmentList = (FragmentList)getSupportFragmentManager().findFragmentById(R.id.activity_building_fragment_list);
        FragmentDetail fragmentDetail = (FragmentDetail)getSupportFragmentManager().findFragmentById(R.id.activity_building_fragment_detail);

        if(fragmentDetail == null || !fragmentDetail.isInLayout()) {
            Intent intent = new Intent(getApplicationContext(), BuildingDetail.class);
            Gson gson = new Gson();
            String json = gson.toJson(buildingList.get(position));
            intent.putExtra("building", json);
            startActivity(intent);
        } else {
            Log.d("OuterSpace", "ShowFragment");
            fragmentDetail.setBuilding(buildingList.get(position));
        }
    }

    public void createBuilding(Building building) {
        presenter.createBuilding(getToken(), building.getBuildingId());
    }
}
