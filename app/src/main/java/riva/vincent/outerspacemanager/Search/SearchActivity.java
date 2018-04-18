package riva.vincent.outerspacemanager.Search;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Api.Responses.Models.Search;
import riva.vincent.outerspacemanager.Building.BuildingActivity;
import riva.vincent.outerspacemanager.Building.BuildingListAdapter;
import riva.vincent.outerspacemanager.Building.BuildingPresenter;
import riva.vincent.outerspacemanager.Building.BuildingPresenterImpl;
import riva.vincent.outerspacemanager.R;

/**
 * Created by vriva on 18/04/2018.
 */

public class SearchActivity extends Activity implements SearchView {


    private RecyclerView searchRecycleView;
    private SearchPresenter presenter;
    private List<Search> searchesList;
    private SearchListAdapter adapter;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_searches);

        searchesList = new ArrayList<>();

        searchRecycleView = findViewById(R.id.searchRecyclerView);

        adapter = new SearchListAdapter(getApplicationContext(), searchesList);
        searchRecycleView.setAdapter(adapter);
        searchRecycleView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new SearchPresenterImpl(this);
        presenter.getSearches(getToken());

        adapter.setOnClickListener(new SearchListAdapter.ClickListener() {
            @Override
            public void onClick(final int position, View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        SearchActivity.this);

                // set title
                alertDialogBuilder.setTitle("Construire");

                final Search search = searchesList.get(position);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Êtes-vous sûr de vouloir construire \"" + search.getName() + "\" ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.createSearch(getToken(), search.getSearchId());
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

    @Override
    protected void onStart() {
        super.onStart();
        handler = new Handler();
        handler.postDelayed(runnable, 1000);
    }

    private void updateChanges() {
        presenter.getSearches(getToken());
        Log.d("OuterSpace", "updateChanges: Refresh");
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            updateChanges();
            handler.postDelayed(this, 10000); // 5 seconds
        }
    };

    private String getToken() {
        SharedPreferences settings = getSharedPreferences("OuterSpaceManager", 0);
        return settings.getString("token", "");
    }

    @Override
    public void showToastFailure() {
        Api.showFailureToast(getApplicationContext());
    }

    @Override
    public void displaySearchesInTheListView(List<Search> searches) {
        searchesList = searches;
        adapter.setValues(searchesList);
        adapter.notifyDataSetChanged();
    }
}
