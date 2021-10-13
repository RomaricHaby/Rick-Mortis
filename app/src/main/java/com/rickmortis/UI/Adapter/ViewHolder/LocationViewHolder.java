package com.rickmortis.UI.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rickmortis.Model.Location.Location;
import com.rickmortis.R;

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

    public void updateLocation(Location location){
        name.setText(location.getName());
        type.setText(location.getType());
        dimension.setText(location.getDimension());
    }
}
