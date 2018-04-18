package riva.vincent.outerspacemanager.Building;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.R;

public class BuildingActivity extends Activity implements BuildingView {

    private RecyclerView buildingListView;
    private BuildingPresenter presenter;
    private List<Building> buildingList;
    private BuildingListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_building);

        buildingList = new ArrayList<>();

        buildingListView = findViewById(R.id.buildingRecyclerView);

        adapter = new BuildingListAdapter(getApplicationContext(), buildingList);
        buildingListView.setAdapter(adapter);
        buildingListView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new BuildingPresenterImpl(this);
        presenter.getBuildings(getToken());

        adapter.setOnClickListener(new BuildingListAdapter.ClickListener() {
            @Override
            public void onClick(final int position, View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        BuildingActivity.this);

                // set title
                alertDialogBuilder.setTitle("Construire");

                Building building = buildingList.get(position);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Êtes-vous sûr de vouloir construire \"" + building.getName() + "\" ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.createBuilding(getToken(), position);
                            }
                        })
                        .setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    private String getToken() {
        SharedPreferences settings = getSharedPreferences("OuterSpaceManager", 0);
        return settings.getString("token", "");
    }

    @Override
    public void showToastFailure() {
        Api.showFailureToast(getApplicationContext());
    }

    @Override
    public void displayBuildingInTheListView(List<Building> buildings) {
        buildingList = buildings;
        adapter.setValues(buildings);
        adapter.notifyDataSetChanged();
    }
}
