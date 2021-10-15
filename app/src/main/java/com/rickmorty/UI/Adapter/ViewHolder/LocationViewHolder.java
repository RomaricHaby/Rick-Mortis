package com.rickmorty.UI.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rickmorty.Model.Location.Locations;
import com.rickmorty.R;

public class LocationViewHolder extends RecyclerView.ViewHolder{
    private final TextView name;
    private final TextView type;
    private final TextView dimension;

    public LocationViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name_location_tv);
        type = itemView.findViewById(R.id.name_type_tv);
        dimension = itemView.findViewById(R.id.dimension_location_tv);
    }

    public void updateLocation(Locations locations){
        name.setText(locations.getName());
        type.setText(locations.getType());
        dimension.setText(locations.getDimension());
    }
}
