package com.example.lab6_b_room;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private LocationDatabase db;
    private List<com.example.lab6_b_room.Location> locations = new ArrayList<Location>();

    public LocationAdapter(Context context, LocationDatabase locationDatabase){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.db = locationDatabase;
        this.locations = db.locationDAO().getAll();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.example.lab6_b_room.LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new LocationViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.lab6_b_room.LocationAdapter.LocationViewHolder holder, int position) {
        com.example.lab6_b_room.Location location = locations.get(position);
        holder.tvLocation.setText(location.getName());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.locationDAO().deleteLocation(location);
                locations.remove(position);
                locations = db.locationDAO().getAll();
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, locations.size());
                //holder.itemView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLocation;
        private ImageButton btnUpdate;
        private  ImageButton btnRemove;
        private com.example.lab6_b_room.LocationAdapter adapter;
        public LocationViewHolder(@NonNull View itemView, com.example.lab6_b_room.LocationAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            tvLocation = itemView.findViewById(R.id.txtLocation);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
