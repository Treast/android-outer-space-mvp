package riva.vincent.outerspacemanager.Building;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Building;
import riva.vincent.outerspacemanager.R;

public class BuildingListAdapter extends RecyclerView.Adapter<BuildingListAdapter.ViewHolder> {
    private Context context;
    private List<Building> values;
    private static ClickListener clickListener;

    public BuildingListAdapter(Context context, List<Building> values) {
        this.context = context;
        this.values = values;
        Log.d("OuterSpace", "BuildingListAdapter: ij");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView timeTextView;
        public TextView gasTextView;
        public TextView mineralsTextView;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.buildingNameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            gasTextView = itemView.findViewById(R.id.gasTextView);
            mineralsTextView = itemView.findViewById(R.id.mineralsTextView);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            BuildingListAdapter.clickListener.onClick(getAdapterPosition(), v);
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
        Building building = values.get(position);

        TextView nameTextView = holder.nameTextView;
        TextView timeTextView = holder.timeTextView;
        TextView gasTextView = holder.gasTextView;
        TextView mineralsTextView = holder.mineralsTextView;
        ImageView imageView = holder.imageView;

        if(building.getBuilding()) {
            nameTextView.setText(building.getName() + " (En cours)");
        } else {
            nameTextView.setText(building.getName() + " (Level " + building.getLevel() + ")");
        }
        timeTextView.setText(building.getTimeToBuildByLevel().toString() + " secondes");
        gasTextView.setText("Gas: " + building.getGasCostByLevel().toString());
        mineralsTextView.setText("Minerals: " + building.getMineralCostByLevel().toString());

        Picasso.get().load(building.getImageUrl()).into(imageView);
    }

    @Override
    public int getItemCount() {
        Log.d("OuterSpace", "getItemCount: " + values.size());
        return values.size();
    }

    public void setValues(List<Building> buildings) {
        this.values = buildings;
    }

    public void setOnClickListener(ClickListener clickListener) {
        BuildingListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(int position, View v);
    }
}
