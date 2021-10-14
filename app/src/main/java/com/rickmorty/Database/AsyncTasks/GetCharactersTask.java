package com.rickmorty.Database.AsyncTasks;


import android.os.AsyncTask;

import com.rickmorty.Database.RickMortyDao;
import com.rickmorty.Model.Character.Character;

import java.util.List;

import javax.security.auth.callback.Callback;

public class GetCharactersTask extends AsyncTask<Object, Void, List<Character>> {

    private DatabaseCallback callback;

    public GetCharactersTask (){}

    @Override
    protected List<Character> doInBackground(Object... objects) {
        callback = (DatabaseCallback) objects[0];
        RickMortyDao rickMortyDao = (RickMortyDao) objects[1];

        return rickMortyDao.getCharacters();
    }


    protected void onPostExecute(List<Character> characters) {
        callback.onResponse(characters);
    }
}
