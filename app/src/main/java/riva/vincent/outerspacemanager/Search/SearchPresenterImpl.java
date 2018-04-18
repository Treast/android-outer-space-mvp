package riva.vincent.outerspacemanager.Search;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riva.vincent.outerspacemanager.Api.Api;
import riva.vincent.outerspacemanager.Api.Responses.BuildingCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.BuildingListResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchCreateResponse;
import riva.vincent.outerspacemanager.Api.Responses.SearchListResponse;
import riva.vincent.outerspacemanager.Building.BuildingView;

/**
 * Created by vriva on 18/04/2018.
 */

public class SearchPresenterImpl implements SearchPresenter {

    private SearchView searchView;

    public SearchPresenterImpl(SearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void getSearches(String token) {
        Call<SearchListResponse> buildingList = Api.getInstance().getSearchList(token);

        buildingList.enqueue(new Callback<SearchListResponse>() {
            @Override
            public void onResponse(Call<SearchListResponse> call, Response<SearchListResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                if(response.body() != null) {
                    Log.d("OuterSpace", "onResponse: There is buildings " + response.body().getSearches().size());
                    searchView.displaySearchesInTheListView(response.body().getSearches());
                }
            }

            @Override
            public void onFailure(Call<SearchListResponse> call, Throwable t) {
                searchView.showToastFailure();
            }
        });
    }

    @Override
    public void createSearch(String token, Integer searchId) {
        Call<SearchCreateResponse> buildingCreate = Api.getInstance().searchCreate(token, searchId);

        buildingCreate.enqueue(new Callback<SearchCreateResponse>() {
            @Override
            public void onResponse(Call<SearchCreateResponse> call, Response<SearchCreateResponse> response) {
                Log.d("OuterSpace", "onResponse: " + response);
                if(response.body() != null) {

                }
            }

            @Override
            public void onFailure(Call<SearchCreateResponse> call, Throwable t) {
                searchView.showToastFailure();
            }
        });
    }
}
