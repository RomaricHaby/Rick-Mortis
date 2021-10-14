package com.rickmorty.Database.AsyncTasks;

import android.os.AsyncTask;

import com.rickmorty.Database.RickMortyDao;
import com.rickmorty.Model.Episode.DataEpisodeApi;
import com.rickmorty.Model.Location.DataLocationApi;


public class AddEpisodesTask extends AsyncTask<Object, Void, Void> {

    private DatabaseCallback callback;

    /**
     * @param[0] => Callback
     * @param[1] => ApiResponseCharacters
     * @param[2] => rickMortyDao
     * @return
     */
    @Override
    protected Void doInBackground(Object... objects) {
        callback = (DatabaseCallback) objects[0];
        DataEpisodeApi dataEpisodeApi = (DataEpisodeApi) objects[1];
        RickMortyDao rickMortyDao = (RickMortyDao) objects[2];

        rickMortyDao.addAllEpisodes(dataEpisodeApi.getResults());

        return null;
    }

    protected void onPostExecute(){
        callback.onResponse();
    }
}
