package riva.vincent.outerspacemanager.Shipyard;
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
import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;
import riva.vincent.outerspacemanager.R;

public class ShipyardListAdapter extends RecyclerView.Adapter<ShipyardListAdapter.ViewHolder> {
    private Context context;
    private List<Ship> values;
    private static ClickListener clickListener;

    public ShipyardListAdapter(Context context, List<Ship> values) {
        this.context = context;
        this.values = values;
        Log.d("OuterSpace", "SearchListAdapter: ij");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView quantityTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.fleetNameTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ShipyardListAdapter.clickListener.onClick(getAdapterPosition(), v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("OuterSpace", "onBindViewHolder: CreateView");
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_fleet, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("OuterSpace", "onBindViewHolder: Testing");
        Ship building = values.get(position);
        Log.d("OuterSpace", "onBindViewHolder: " + building.getName());
        Log.d("OuterSpace", "onBindViewHolder: " + building.getAmount());

        TextView nameTextView = holder.nameTextView;
        TextView quantityTextView = holder.quantityTextView;


        quantityTextView.setText(building.getAmount().toString());
        nameTextView.setText(building.getName());

    }

    @Override
    public int getItemCount() {
        Log.d("OuterSpace", "getItemCount: " + values.size());
        return values.size();
    }

    public void setValues(List<Ship> buildings) {
        this.values = buildings;
    }

    public void setOnClickListener(ClickListener clickListener) {
        ShipyardListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(int position, View v);
    }
}
