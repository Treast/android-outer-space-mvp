package riva.vincent.outerspacemanager.Fleet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Api.Responses.Models.Fleet;
import riva.vincent.outerspacemanager.R;

public class FleetActivity extends Activity implements FleetView {

    private FleetPresenter presenter;
    private RecyclerView fleetListView;
    private List<Fleet> buildingList;
    private FleetListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fleet);

        buildingList = new ArrayList<>();

        fleetListView = findViewById(R.id.fleetRecyclerView);

        adapter = new FleetListAdapter(getApplicationContext(), buildingList);
        fleetListView.setAdapter(adapter);
        fleetListView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new FleetPresenterImpl(this);
        presenter.getFleet(getToken());

        adapter.setOnClickListener(new FleetListAdapter.ClickListener() {
            @Override
            public void onClick(final int position, View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        FleetActivity.this);

                // set title
                alertDialogBuilder.setTitle("Construire");

                Fleet building = buildingList.get(position);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Êtes-vous sûr de vouloir construire \"" + building.getName() + "\" ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.createShip(getToken(), position);
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
    public void displayFleetInTheListView(List<Fleet> buildings) {
        buildingList = buildings;
        adapter.setValues(buildings);
        adapter.notifyDataSetChanged();
    }
}
