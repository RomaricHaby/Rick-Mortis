package com.rickmorty.Database.AsyncTasks;


import android.os.AsyncTask;

import com.rickmorty.Database.RickMortyDao;
import com.rickmorty.Model.Location.Locations;

import java.util.List;

public class GetLocationsTask extends AsyncTask<Object, Void, List<Locations>> {

    private DatabaseCallback callback;

    @Override
    protected List<Locations> doInBackground(Object... objects) {
        callback = (DatabaseCallback) objects[0];
        RickMortyDao rickMortyDao= (RickMortyDao) objects[1];

        return rickMortyDao.getLocations();
    }


    protected void onPostExecute(List<Locations> locations) {
        callback.onResponse(locations);
    }
}
