package com.rickmortis.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rickmortis.Model.Episode.Episode;
import com.rickmortis.Model.Location.Location;
import com.rickmortis.R;
import com.rickmortis.UI.Adapter.ViewHolder.EpisodeViewHolder;
import com.rickmortis.UI.Adapter.ViewHolder.LocationViewHolder;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeViewHolder>{

    private final List<Episode> episodeList;


    public EpisodeAdapter(List<Episode> episodes) {
        this.episodeList = episodes;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_holder_episode, parent, false);

        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder viewHolder, int position) {
        viewHolder.updateEpisode(this.episodeList.get(position));
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public void addData(List<Episode> list){
        episodeList.addAll(list);
    }
}
