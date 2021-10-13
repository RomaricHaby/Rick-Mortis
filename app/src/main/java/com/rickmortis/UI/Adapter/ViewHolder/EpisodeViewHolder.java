package com.rickmortis.UI.Adapter.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rickmortis.Model.Episode.Episode;
import com.rickmortis.R;

public class EpisodeViewHolder extends RecyclerView.ViewHolder{

    private final TextView nameEpisode;
    private final TextView episodeNum;
    private final TextView airdate;


    public EpisodeViewHolder(@NonNull View itemView) {
        super(itemView);

        nameEpisode = itemView.findViewById(R.id.tv_name_episode);
        episodeNum = itemView.findViewById(R.id.tv_episode);
        airdate = itemView.findViewById(R.id.tv_air_date);
    }

    public void updateEpisode(Episode episode){
        nameEpisode.setText(episode.getName());
        episodeNum.setText(episode.getEpisode());
        airdate.setText(episode.getAirDate());
    }

}
