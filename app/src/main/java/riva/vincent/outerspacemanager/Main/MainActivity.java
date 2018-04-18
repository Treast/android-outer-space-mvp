package riva.vincent.outerspacemanager.Main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Building.BuildingActivity;
import riva.vincent.outerspacemanager.Building.Fragments.BuildingMain;
import riva.vincent.outerspacemanager.Fleet.FleetActivity;
import riva.vincent.outerspacemanager.R;
import riva.vincent.outerspacemanager.Search.SearchActivity;
import riva.vincent.outerspacemanager.Shipyard.ShipyardActivity;

/**
 * Created by treast on 27/03/2018.
 */

public class MainActivity extends Activity implements MainView, View.OnClickListener {

    private MainPresenter presenter;
    private TextView usernameTextView;
    private TextView pointsTextView;

    private Button buildingButton;
    private Button fleetButton;
    private Button shipyardButton;
    private Button searchButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);

        usernameTextView = findViewById(R.id.usernameTextView);
        pointsTextView = findViewById(R.id.pointsTextView);

        presenter.getCurrentUser(getToken());

        buildingButton = findViewById(R.id.buildingButton);
        fleetButton = findViewById(R.id.fleetButton);
        shipyardButton = findViewById(R.id.shipyardButton);
        searchButton = findViewById(R.id.researchButton);

        buildingButton.setOnClickListener(this);
        fleetButton.setOnClickListener(this);
        shipyardButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
    }

    private String getToken() {
        SharedPreferences settings = getSharedPreferences("OuterSpaceManager", 0);
        return settings.getString("token", "");
    }

    @Override
    public void updateCurrentUserInformations(String username, Long points) {
        usernameTextView.setText(username);
        pointsTextView.setText("Points: " + points.toString());
    }

    @Override
    public void showToastFailure() {
        Api.showFailureToast(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buildingButton:
                Intent intent = new Intent(getApplicationContext(), BuildingMain.class);
                startActivity(intent);
                break;
            case R.id.fleetButton:
                Intent intentMyFleet = new Intent(getApplicationContext(), FleetActivity.class);
                startActivity(intentMyFleet);
                break;
            case R.id.shipyardButton:
                Intent intentFleet = new Intent(getApplicationContext(), ShipyardActivity.class);
                startActivity(intentFleet);
                break;
            case R.id.researchButton:
                Intent intentSearch = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intentSearch);
                break;
        }
    }
}
