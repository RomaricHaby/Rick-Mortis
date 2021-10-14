package com.rickmorty.Database.AsyncTasks;

import android.os.AsyncTask;

import com.rickmorty.Database.RickMortyDao;
import com.rickmorty.Model.Character.DataCharacterApi;


public class AddCharactersTask extends AsyncTask<Object, Void, Void> {

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
        DataCharacterApi responseCharacters = (DataCharacterApi) objects[1];
        RickMortyDao rickMortyDao = (RickMortyDao) objects[2];

        rickMortyDao.addAllCharacters(responseCharacters.getCharacters());

        return null;
    }

    protected void onPostExecute(){
        callback.onResponse();
    }
}
