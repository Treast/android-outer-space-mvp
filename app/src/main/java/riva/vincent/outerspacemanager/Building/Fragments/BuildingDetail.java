package riva.vincent.outerspacemanager.Building.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.gson.Gson;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.R;

/**
 * Created by vriva on 18/04/2018.
 */

public class BuildingDetail extends FragmentActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);
        String buildingString = getIntent().getStringExtra("building");

        Gson gson = new Gson();
        Building building = gson.fromJson(buildingString, Building.class);
        FragmentDetail fragmentDetail = (FragmentDetail)getSupportFragmentManager().findFragmentById(R.id.activity_building_fragment_detail);

        if(fragmentDetail != null && fragmentDetail.isInLayout()) {
            fragmentDetail.setBuilding(building);
        }
    }
}
