package riva.vincent.outerspacemanager.galaxy;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import riva.vincent.outerspacemanager.Api.Responses.Models.Ship;
import riva.vincent.outerspacemanager.Api.Responses.Models.User;
import riva.vincent.outerspacemanager.R;

public class GalaxyListAdapter extends RecyclerView.Adapter<GalaxyListAdapter.ViewHolder> {
    private Context context;
    private List<User> values;
    private static ClickListener clickListener;

    public GalaxyListAdapter(Context context, List<User> values) {
        this.context = context;
        this.values = values;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView usernameTextView;
        public TextView pointsTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.textViewUsername);
            pointsTextView = itemView.findViewById(R.id.textViewPoints);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            GalaxyListAdapter.clickListener.onClick(getAdapterPosition(), v);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("OuterSpace", "onBindViewHolder: CreateView");
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_galaxy, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = values.get(position);

        TextView usernameTextView = holder.usernameTextView;
        TextView pointsTextView = holder.pointsTextView;


        pointsTextView.setText(user.getPoints() + " points");
        usernameTextView.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        Log.d("OuterSpace", "getItemCount: " + values.size());
        return values.size();
    }

    public void setValues(List<User> users) {
        this.values = users;
    }

    public void setOnClickListener(ClickListener clickListener) {
        GalaxyListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(int position, View v);
    }
}
