package riva.vincent.outerspacemanager.Main;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import co.revely.gradient.RevelyGradient;
import co.revely.gradient.drawables.Gradient;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.DeviceAddRequest;
import riva.vincent.outerspacemanager.Api.Responses.SearchCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.UsersGetResponse;
import riva.vincent.outerspacemanager.Building.BuildingActivity;
import riva.vincent.outerspacemanager.Building.Fragments.BuildingMain;
import riva.vincent.outerspacemanager.Fleet.FleetActivity;
import riva.vincent.outerspacemanager.R;
import riva.vincent.outerspacemanager.Search.SearchActivity;
import riva.vincent.outerspacemanager.Shipyard.ShipyardActivity;
import riva.vincent.outerspacemanager.galaxy.GalaxyActivity;
import riva.vincent.outerspacemanager.report.ReportActivity;

/**
 * Created by treast on 27/03/2018.
 */

public class MainActivity extends Activity implements MainView, View.OnClickListener {

    private MainPresenter presenter;
    private TextView usernameTextView;
    private TextView pointsTextView;

    private TextView gasTextView;
    private TextView mineralsTextView;
    private TextView gasModifierTextView;
    private TextView mineralsModifierTextView;

    private Button buildingButton;
    private Button fleetButton;
    private Button shipyardButton;
    private Button searchButton;
    private Button galaxyButton;
    private Button reportButton;

    private double gas;
    private double minerals;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 360f);
        valueAnimator.setDuration(15000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());

        RevelyGradient
                .linear()
                .colors(new int[] {Color.parseColor("#00076f"), Color.parseColor("#44008b"), Color.parseColor("#9f45b0"), Color.parseColor("#e54ed0")})
                .animate(valueAnimator, new Function2<ValueAnimator, Gradient, Unit>() {

                    @Override
                    public Unit invoke(ValueAnimator valueAnimator, Gradient gradient) {
                        gradient.setAngle((float)valueAnimator.getAnimatedValue());
                        return null;
                    }
                })
                .onBackgroundOf(findViewById(R.id.scrollView));
        valueAnimator.start();

        presenter = new MainPresenterImpl(this);

        usernameTextView = findViewById(R.id.usernameTextView);
        pointsTextView = findViewById(R.id.pointsTextView);

        gasTextView = findViewById(R.id.gasTextView);
        mineralsTextView = findViewById(R.id.mineralsTextView);
        gasModifierTextView = findViewById(R.id.gasModifierTextView);
        mineralsModifierTextView = findViewById(R.id.mineralsModifierTextView);

        presenter.getCurrentUser(getToken());

        buildingButton = findViewById(R.id.buildingButton);
        fleetButton = findViewById(R.id.fleetButton);
        shipyardButton = findViewById(R.id.shipyardButton);
        searchButton = findViewById(R.id.researchButton);
        galaxyButton = findViewById(R.id.galaxyButton);
        reportButton = findViewById(R.id.reportButton);

        buildingButton.setOnClickListener(this);
        fleetButton.setOnClickListener(this);
        shipyardButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        galaxyButton.setOnClickListener(this);
        reportButton.setOnClickListener(this);
    }

    private String getToken() {
        SharedPreferences settings = getSharedPreferences("OuterSpaceManager", 0);
        return settings.getString("token", "");
    }

    @Override
    public void updateCurrentUserInformations(String username, Long points, float gas, float minerals, float gasModifier, float mineralsModifier) {
        usernameTextView.setText(username);
        pointsTextView.setText("Points: " + points.toString());
        gasTextView.setText("Gas: " + String.valueOf(Math.round(gas)));
        mineralsTextView.setText("Minerals: " + String.valueOf(Math.round(minerals)));
        gasModifierTextView.setText("Gas: " + gasModifier + "/h");
        mineralsModifierTextView.setText("Minerals : " + mineralsModifier + "/h");

        this.minerals = minerals;
        this.gas = gas;
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
                intentFleet.putExtra("gas", gas);
                intentFleet.putExtra("minerals", minerals);
                startActivity(intentFleet);
                break;
            case R.id.researchButton:
                Intent intentSearch = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.galaxyButton:
                Intent intentGalaxy = new Intent(getApplicationContext(), GalaxyActivity.class);
                startActivity(intentGalaxy);
                break;
            case R.id.reportButton:
                Intent intentReport = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(intentReport);
                break;
        }
    }
}
