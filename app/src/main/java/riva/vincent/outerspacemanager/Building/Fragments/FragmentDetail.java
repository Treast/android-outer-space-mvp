package riva.vincent.outerspacemanager.Building.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Building.BuildingActivity;
import riva.vincent.outerspacemanager.R;

/**
 * Created by vriva on 18/04/2018.
 */

public class FragmentDetail extends Fragment {

    private TextView levelTextView;
    private TextView amountTextView;
    private TextView timeTextView;
    private LinearLayout linearLayout;
    private Button buildButton;
    private Building building;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_building_fragment_detail, container);

        levelTextView = v.findViewById(R.id.detailLevelText);
        amountTextView = v.findViewById(R.id.detailAmountText);
        timeTextView = v.findViewById(R.id.detailTimeText);
        linearLayout = v.findViewById(R.id.detailLinearLayout);
        buildButton = v.findViewById(R.id.detailBuildButton);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set title
                alertDialogBuilder.setTitle("Construire");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Êtes-vous sûr de vouloir construire \"" + building.getName() + "\" ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ((BuildingMain)getActivity()).createBuilding(building);
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

    public void setBuilding(Building building) {
        this.building = building;
        levelTextView.setText(building.getLevel().toString());
        amountTextView.setText(String.valueOf(building.getTotalAmount()));
        timeTextView.setText(String.valueOf(building.getTotalTime()));
        linearLayout.setVisibility(View.VISIBLE);
        Log.d("OuterSpace", "SetBuilding");
    }

    public void setBuilding(String string) {
        Gson gson = new Gson();
        Building building = gson.fromJson(string, Building.class);
        setBuilding(building);
    }
}
