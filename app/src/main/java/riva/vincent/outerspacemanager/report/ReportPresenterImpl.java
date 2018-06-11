package riva.vincent.outerspacemanager.report;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.Models.Report;
import riva.vincent.outerspacemanager.Api.Responses.ReportGetResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchListResponse;

/**
 * Created by vriva on 18/04/2018.
 */

public class ReportPresenterImpl implements ReportPresenter {

    private ReportView reportView;

    public ReportPresenterImpl(ReportView reportView) {
        this.reportView = reportView;
    }

    @Override
    public void getReports(String token) {
        Call<ReportGetResponse> buildingList = Api.getInstance().getReports(token);
        Log.d("OuterSpace", "onResponse: Begin report");

        buildingList.enqueue(new Callback<ReportGetResponse>() {
            @Override
            public void onResponse(Call<ReportGetResponse> call, Response<ReportGetResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                if(response.body() != null) {
                    Log.d("OuterSpace", "onResponse: There is buildings " + response.body().getReports().size());
                    reportView.displaySearchesInTheListView(response.body().getReports());
                } else {
                    Log.d("OuterSpace", "onResponse: Failed body report");
                }
            }

            @Override
            public void onFailure(Call<ReportGetResponse> call, Throwable t) {
                Log.d("OuterSpace", "onResponse: Error body report: " + t.getMessage());
                reportView.showToastFailure();
            }
        });
    }
}
