package riva.vincent.outerspacemanager.Search;

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
import riva.vincent.outerspacemanager.Api.Responses.Models.Search;
import riva.vincent.outerspacemanager.R;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {
    private Context context;
    private List<Search> values;
    private static ClickListener clickListener;

    public SearchListAdapter(Context context, List<Search> values) {
        this.context = context;
        this.values = values;
        Log.d("OuterSpace", "SearchListAdapter: ij");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameTextView;
        public TextView timeTextView;
        public TextView gasTextView;
        public TextView mineralsTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.searchNameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            gasTextView = itemView.findViewById(R.id.gasTextView);
            mineralsTextView = itemView.findViewById(R.id.mineralsTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            SearchListAdapter.clickListener.onClick(getAdapterPosition(), v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("OuterSpace", "onBindViewHolder: CreateView");
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_search, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Search search = values.get(position);
        Log.d("OuterSpace", "onBindViewHolder: Testing: " + search.getName());

        TextView nameTextView = holder.nameTextView;
        TextView timeTextView = holder.timeTextView;
        TextView gasTextView = holder.gasTextView;
        TextView mineralsTextView = holder.mineralsTextView;

        if(search.getBuilding()) {
            nameTextView.setText(search.getName() + " (En cours)");
        } else {
            nameTextView.setText(search.getName() + " (Level " + search.getLevel() + ")");
        }
        timeTextView.setText((search.getTimeToBuildByLevel() * search.getLevel() + search.getTimeToBuildLevel0()) + " secondes");
        gasTextView.setText("Gas: " + (search.getGasCostByLevel() * search.getLevel() + search.getGasCostLevel0()));
        mineralsTextView.setText("Minerals: " + (search.getMineralCostByLevel() * search.getLevel() + search.getMineralCostLevel0()));
    }

    @Override
    public int getItemCount() {
        Log.d("OuterSpace", "getItemCount: " + values.size());
        return values.size();
    }

    public void setValues(List<Search> buildings) {
        this.values = buildings;
    }

    public void setOnClickListener(ClickListener clickListener) {
        SearchListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(int position, View v);
    }
}
