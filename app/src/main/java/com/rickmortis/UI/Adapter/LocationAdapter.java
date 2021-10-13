package com.rickmortis.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rickmortis.Model.Character.Character;
import com.rickmortis.Model.Location.Location;
import com.rickmortis.R;
import com.rickmortis.UI.Activity.MainActivity;
import com.rickmortis.UI.Adapter.ViewHolder.LocationViewHolder;

import java.util.List;

public class LocationAdapter  extends RecyclerView.Adapter<LocationViewHolder> {

    private final List<Location> locationList;


    public LocationAdapter(List<Location> locations) {
        this.locationList = locations;
    }


    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_holder_location, parent, false);

        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder viewHolder, int position) {
        viewHolder.updateLocation(this.locationList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public void addData(List<Location> list){
        locationList.addAll(list);
    }
}
