package riva.vincent.outerspacemanager.report;

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
import riva.vincent.outerspacemanager.Api.Responses.Models.Report;
import riva.vincent.outerspacemanager.Api.Responses.Models.Search;
import riva.vincent.outerspacemanager.R;

/**
 * Created by vriva on 18/04/2018.
 */

public class ReportActivity extends Activity implements ReportView {


    private RecyclerView reportRecycleView;
    private ReportPresenter presenter;
    private List<Report> reportList;
    private ReportListAdapter adapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report);

        reportList = new ArrayList<>();

        reportRecycleView = findViewById(R.id.reportRecyclerView);

        adapter = new ReportListAdapter(getApplicationContext(), reportList);
        reportRecycleView.setAdapter(adapter);
        reportRecycleView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new ReportPresenterImpl(this);
        presenter.getReports(getToken());
    }

    private void updateChanges() {
        presenter.getReports(getToken());
        Log.d("OuterSpace", "updateChanges: Refresh");
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
    public void displaySearchesInTheListView(List<Report> reports) {
        reportList = reports;
        adapter.setValues(reportList);
        adapter.notifyDataSetChanged();
    }
}
