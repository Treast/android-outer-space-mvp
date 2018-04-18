package riva.vincent.outerspacemanager.Building.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Building.BuildingListAdapter;
import riva.vincent.outerspacemanager.R;

public class FragmentList extends Fragment {

    private RecyclerView recyclerView;
    private List<Building> buildingList;
    private BuildingListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("OuterSpace", "InitFragment");
        View v = inflater.inflate(R.layout.activity_building_fragment_list, container);
        Log.d("OuterSpace", "SetView");
        recyclerView = v.findViewById(R.id.buildingFragmentRecyclerView);
        Log.d("OuterSpace", "GetRecyclerView");
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        buildingList = new ArrayList<>();
        adapter = new BuildingListAdapter((BuildingMain)getActivity(), buildingList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter.setOnClickListener((BuildingMain)getActivity());
    }

    public void displayBuildingInTheListView(List<Building> buildings) {
        buildingList = buildings;
        adapter.setValues(buildings);
        adapter.notifyDataSetChanged();
    }
}
