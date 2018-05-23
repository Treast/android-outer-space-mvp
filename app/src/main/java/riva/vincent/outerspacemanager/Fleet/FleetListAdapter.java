package riva.vincent.outerspacemanager.Fleet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.Api.Responses.Models.Fleet;
import riva.vincent.outerspacemanager.R;

public class FleetListAdapter extends RecyclerView.Adapter<FleetListAdapter.ViewHolder> {
    private Context context;
    private List<Fleet> values;
    private static ClickListener clickListener;

    public FleetListAdapter(Context context, List<Fleet> values) {
        this.context = context;
        this.values = values;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView timeTextView;
        public TextView gasTextView;
        public TextView mineralsTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.buildingNameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            gasTextView = itemView.findViewById(R.id.gasTextView);
            mineralsTextView = itemView.findViewById(R.id.mineralsTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            FleetListAdapter.clickListener.onClick(getAdapterPosition(), v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("OuterSpace", "onBindViewHolder: CreateView");
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_building, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("OuterSpace", "onBindViewHolder: Testing");
        Fleet building = values.get(position);

        TextView nameTextView = holder.nameTextView;
        TextView timeTextView = holder.timeTextView;
        TextView gasTextView = holder.gasTextView;
        TextView mineralsTextView = holder.mineralsTextView;

        timeTextView.setText((building.getTimeToBuild()) + " secondes");
        nameTextView.setText(building.getName());
        gasTextView.setText("Gas: " + (building.getGasCost()));
        mineralsTextView.setText("Minerals: " + (building.getMineralCost()));

    }

    @Override
    public int getItemCount() {
        Log.d("OuterSpace", "getItemCount: " + values.size());
        return values.size();
    }

    public void setValues(List<Fleet> buildings) {
        this.values = buildings;
    }

    public void setOnClickListener(ClickListener clickListener) {
        FleetListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(int position, View v);
    }
}
