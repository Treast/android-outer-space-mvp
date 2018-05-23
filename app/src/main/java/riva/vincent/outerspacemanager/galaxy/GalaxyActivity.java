package riva.vincent.outerspacemanager.galaxy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;
import riva.vincent.outerspacemanager.Api.Responses.Models.Search;
import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;
import riva.vincent.outerspacemanager.Api.Responses.Models.User;
import riva.vincent.outerspacemanager.R;
import riva.vincent.outerspacemanager.Search.SearchListAdapter;
import riva.vincent.outerspacemanager.Search.SearchPresenter;
import riva.vincent.outerspacemanager.Shipyard.ShipyardActivity;

public class GalaxyActivity extends Activity implements GalaxyView, View.OnClickListener {

    private GalaxyPresenter presenter;
    private RecyclerView galaxyRecycleView;
    private List<User> usersList;
    private GalaxyListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_galaxy);
        usersList = new ArrayList<>();

        galaxyRecycleView = findViewById(R.id.recyclerViewGalaxy);

        adapter = new GalaxyListAdapter(getApplicationContext(), usersList);
        galaxyRecycleView.setAdapter(adapter);
        galaxyRecycleView.setLayoutManager(new LinearLayoutManager(this));


        presenter = new GalaxyPresenterImpl(this);

        presenter.getUsers(getToken());

        SwipeToDeleteCallback handler = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.notifyDataSetChanged();
                Log.d("OuterSpace", "onSwiped: " + viewHolder.getLayoutPosition());
                String username = usersList.get(viewHolder.getLayoutPosition()).getUsername();
                presenter.attackUser(getToken(), username);
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(handler);
        helper.attachToRecyclerView(galaxyRecycleView);
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
    public void displayUsersInTheListView(ArrayList<User> users) {
        usersList = users;
        adapter.setValues(usersList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSuccessAttack(String code, double time) {
        Toast.makeText(getApplicationContext(), "Attack: " + code + " in " + String.valueOf(time - System.currentTimeMillis()/1000) + " seconds !", Toast.LENGTH_LONG);
    }



    @Override
    public void onClick(View v) {

    }
}
