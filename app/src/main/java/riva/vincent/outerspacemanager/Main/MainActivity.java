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
import riva.vincent.outerspacemanager.R;

/**
 * Created by treast on 27/03/2018.
 */

public class MainActivity extends Activity implements MainView, View.OnClickListener {

    private MainPresenter presenter;
    private TextView usernameTextView;
    private TextView pointsTextView;

    private Button buildingButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);

        usernameTextView = findViewById(R.id.usernameTextView);
        pointsTextView = findViewById(R.id.pointsTextView);

        presenter.getCurrentUser(getToken());

        buildingButton = findViewById(R.id.buildingButton);

        buildingButton.setOnClickListener(this);
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
                Intent intent = new Intent(getApplicationContext(), BuildingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
