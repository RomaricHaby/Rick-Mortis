package com.rickmorty.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rickmorty.Model.Location.Locations;
import com.rickmorty.R;
import com.rickmorty.UI.Adapter.ViewHolder.LocationViewHolder;

import java.util.List;

public class LocationAdapter  extends RecyclerView.Adapter<LocationViewHolder> {

    private final List<Locations> locationsList;


    public LocationAdapter(List<Locations> locations) {
        this.locationsList = locations;
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
        viewHolder.updateLocation(this.locationsList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }

    public void addData(List<Locations> list){
        locationsList.addAll(list);
    }
}
