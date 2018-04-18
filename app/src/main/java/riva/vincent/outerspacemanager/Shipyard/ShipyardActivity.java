package riva.vincent.outerspacemanager.Shipyard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Api.Responses.Models.Fleet;
import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;
import riva.vincent.outerspacemanager.R;

public class ShipyardActivity extends Activity implements ShipyardView {

    private ShipyardPresenter presenter;
    private RecyclerView fleetListView;
    private List<Ship> buildingList;
    private ShipyardListAdapter adapter;
    private Button attackButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shipyard);

        buildingList = new ArrayList<>();

        fleetListView = findViewById(R.id.fleetRecyclerView);
        attackButton = findViewById(R.id.attackButton);

        adapter = new ShipyardListAdapter(getApplicationContext(), buildingList);
        fleetListView.setAdapter(adapter);
        fleetListView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new ShipyardPresenterImpl(this);
        presenter.getFleet(getToken());

        adapter.setOnClickListener(new ShipyardListAdapter.ClickListener() {
            @Override
            public void onClick(final int position, View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ShipyardActivity.this);

                // set title
                alertDialogBuilder.setTitle("Construire");

                Ship building = buildingList.get(position);

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

        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    public void displayFleetInTheListView(List<Ship> buildings) {
        buildingList = buildings;
        adapter.setValues(buildings);
        adapter.notifyDataSetChanged();
    }
}
