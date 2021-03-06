package riva.vincent.outerspacemanager.Shipyard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;
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

    private double gas;
    private double minerals;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shipyard);

        Intent intent = getIntent();
        gas = intent.getDoubleExtra("gas", 0);
        minerals = intent.getDoubleExtra("minerals", 0);
        Log.d("OuterSpace", "gas: " + gas);
        Log.d("OuterSpace", "minerals: " + minerals);

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
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ShipyardActivity.this);

                final Ship building = buildingList.get(position);
                // set title
                alertDialogBuilder.setTitle("Construire");
                final LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                final SeekBar seek = new SeekBar(getApplicationContext());
                final TextView textView = new TextView(getApplicationContext());

                double numberWithGas = Math.floor(gas / building.getGasCost());
                double numberWithMinerals = Math.floor(minerals / building.getMineralCost());

                int numberMaxShips = (int)Math.min(numberWithGas, numberWithMinerals);

                seek.setMax(numberMaxShips);
                Log.d("OuterSpace", "maxShip: " + numberMaxShips);
                Log.d("OuterSpace", "maxShipGas: " + numberWithGas);
                Log.d("OuterSpace", "maxShipMinerals: " + numberWithMinerals);
                seek.setMinimumHeight(20);
                textView.setPadding(70, 10, 70, 10);

                seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        textView.setText("Nombre à construire: " + progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                layout.addView(textView);
                layout.addView(seek);
                alertDialogBuilder.setView(layout);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Êtes-vous sûr de vouloir construire \"" + building.getName() + "\" ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.createShip(getToken(), building.getShipId(), seek.getProgress());
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ShipyardActivity.this);

                // set title
                alertDialogBuilder.setTitle("Attaquer");
                final EditText input = new EditText(getApplicationContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alertDialogBuilder.setView(input);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Qui voulez-vous attaquer ?")
                        .setCancelable(false)
                        .setPositiveButton("MEURS !",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.cena);
                                mp.start();

                                List<ShipAmount> ships = new ArrayList<>();
                                for(int i = 0; i < buildingList.size(); i++) {
                                    Ship ship = buildingList.get(i);
                                    ships.add(new ShipAmount(ship.getShipId(), ship.getAmount()));
                                    Log.d("OuterSpace", "ships: " + ship.getName() + " " + ship.getAmount());
                                }
                                Log.d("OuterSpace", "Attacking " + input.getText().toString());
                                presenter.attackUser(getToken(), input.getText().toString(), ships);
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
    public void showSuccessAttack(String code, double time) {
        Toast.makeText(getApplicationContext(), "Attack: " + code + " in " + String.valueOf(time - System.currentTimeMillis()/1000) + " seconds !", Toast.LENGTH_LONG);
    }

    @Override
    public void displayFleetInTheListView(List<Ship> buildings) {
        buildingList = buildings;
        adapter.setValues(buildings);
        adapter.notifyDataSetChanged();
    }
}
