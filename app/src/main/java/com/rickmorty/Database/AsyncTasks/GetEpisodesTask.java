package com.rickmorty.Database.AsyncTasks;

import android.os.AsyncTask;
import com.rickmorty.Database.RickMortyDao;
import com.rickmorty.Model.Episode.Episode;
import java.util.List;

public class GetEpisodesTask extends AsyncTask<Object, Void, List<Episode>> {

    private DatabaseCallback callback;

    @Override
    protected List<Episode> doInBackground(Object... objects) {
        callback = (DatabaseCallback) objects[0];
        RickMortyDao rickMortyDao= (RickMortyDao) objects[1];

        return rickMortyDao.getEpisodes();
    }


    protected void onPostExecute(List<Episode> episodes) {
        callback.onResponse(episodes);
    }
}
