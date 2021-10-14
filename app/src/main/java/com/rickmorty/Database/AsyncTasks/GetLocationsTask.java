package com.rickmorty.Database.AsyncTasks;


import android.os.AsyncTask;

import com.rickmorty.Database.RickMortyDao;
import com.rickmorty.Model.Location.Location;

import java.util.List;

public class GetLocationsTask extends AsyncTask<Object, Void, List<Location>> {

    private DatabaseCallback callback;

    @Override
    protected List<Location> doInBackground(Object... objects) {
        callback = (DatabaseCallback) objects[0];
        RickMortyDao rickMortyDao= (RickMortyDao) objects[1];

        return rickMortyDao.getLocations();
    }


    protected void onPostExecute(List<Location> locations) {
        callback.onResponse(locations);
    }
}
