package riva.vincent.outerspacemanager.report;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import riva.vincent.outerspacemanager.Api.Requests.Models.ShipAmount;
import riva.vincent.outerspacemanager.Api.Responses.Models.Report;
import riva.vincent.outerspacemanager.Api.Responses.Models.Search;
import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;
import riva.vincent.outerspacemanager.R;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {
    private Context context;
    private List<Report> values;
    private static ClickListener clickListener;

    public ReportListAdapter(Context context, List<Report> values) {
        this.context = context;
        this.values = values;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView fromTextView;
        public TextView toTextView;
        public TextView gasTextView;
        public TextView mineralsTextView;
        public TextView dateTextView;
        public TextView typeTextView;
        public TextView winOrLossTextView;
        public TextView attackerTextView;
        public TextView defenderTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            fromTextView = itemView.findViewById(R.id.fromTextView);
            toTextView = itemView.findViewById(R.id.toTextView);
            gasTextView = itemView.findViewById(R.id.gasTextView);
            mineralsTextView = itemView.findViewById(R.id.mineralsTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            winOrLossTextView = itemView.findViewById(R.id.winOrLossTextView);
            attackerTextView = itemView.findViewById(R.id.attackerTextView);
            defenderTextView = itemView.findViewById(R.id.defenderTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ReportListAdapter.clickListener.onClick(getAdapterPosition(), v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("OuterSpace", "onBindViewHolder: CreateView");
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_report, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Report report = values.get(position);
        Log.d("OuterSpace", "onBindViewHolder: Testing: " + report);

        TextView fromTextView = holder.fromTextView;
        TextView toTextView = holder.toTextView;
        TextView gasTextView = holder.gasTextView;
        TextView mineralsTextView = holder.mineralsTextView;
        TextView dateTextView = holder.dateTextView;
        TextView typeTextView = holder.typeTextView;
        TextView winOrLossTextView = holder.winOrLossTextView;
        TextView attackerTextView = holder.attackerTextView;
        TextView defenderTextView = holder.defenderTextView;

        Date date = new Date((long)report.getDate() * 1000);


        fromTextView.setText("From: " + report.getFrom());
        toTextView.setText("To: " + report.getTo());
        gasTextView.setText("Gas: " + Math.floor(report.getMineralsWon()));
        mineralsTextView.setText("Minerals: " + Math.floor(report.getGasWon()));
        dateTextView.setText("Date: " + date.getDay() + "/" + date.getMonth() + "/" + date.getYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
        typeTextView.setText("Type: " + report.getType());

        Log.d("OuterSpace", "onBindViewHolder: Type: " + report.getType());
        Log.d("OuterSpace", "onBindViewHolder: Gas: " + report.getGasWon());
        Log.d("OuterSpace", "onBindViewHolder: Minerals: " + report.getMineralsWon());


        if((report.getType().equals("attacked") && report.getGasWon() < 1 && report.getMineralsWon() < 1) || (report.getType().equals("attacker") && (report.getGasWon() > 0.0 || report.getMineralsWon() > 0.0))) {
            winOrLossTextView.setText("Win");
            winOrLossTextView.setTextColor(Color.parseColor("#2ecc71"));
        } else {
            winOrLossTextView.setText("Lose");
            winOrLossTextView.setTextColor(Color.parseColor("#e74c3c"));
        }
        //String result = (report.getType() == "attacked" && report.getGasWon() < 1 && report.getMineralsWon() < 1) || (report.getType() == "attacker" && (report.getGasWon() > 0.0 || report.getMineralsWon() > 0.0)) ? "True" : "False";
        String result = (report.getGasWon() < 1 && report.getMineralsWon() < 1) ? "True" : "False";
        Log.d("OuterSpace", "Reuslt: " + result);

        String attackString = "Attack fleet:\n";
        for(Ship ship : report.getAttackerFleet()) {
            attackString += ship.getName() + " (" + ship.getAmount() + ")\n";
        }
        attackerTextView.setText(attackString);

        String defenseString = "Defense fleet:\n";
        for(Ship ship : report.getDefenderFleet()) {
            defenseString += ship.getName() + " (" + ship.getAmount() + ")\n";
        }
        defenderTextView.setText(defenseString);
    }

    @Override
    public int getItemCount() {
        Log.d("OuterSpace", "getItemCount: " + values.size());
        return values.size();
    }

    public void setValues(List<Report> buildings) {
        this.values = buildings;
    }

    public void setOnClickListener(ClickListener clickListener) {
        ReportListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(int position, View v);
    }
}
