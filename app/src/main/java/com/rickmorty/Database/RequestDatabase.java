package com.rickmorty.Database;


import androidx.room.Room;
import com.rickmorty.Database.AsyncTasks.DatabaseCallback;
import com.rickmorty.UI.Activity.MainActivity;

public class RequestDatabase {

    private AppDatabase database;
    private RickMortyDao rickMortyDao;
    private DatabaseCallback callback;
    private MainActivity mainActivity;

    public static RequestDatabase instance = null;

    private RequestDatabase(){}

    public static RequestDatabase getInstance() {
        if (instance == null) {
            instance = new RequestDatabase();
        }
        return instance;
    }

    public void setDatabase(MainActivity activity){
        this.mainActivity = activity;

        database = Room.databaseBuilder(mainActivity.getApplicationContext(), AppDatabase.class, "RickMortyDatabase").build();
        rickMortyDao = database.rickMortyDao();
        callback = new DatabaseCallback(mainActivity);
    }

    //Get && Set

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public RickMortyDao getRickMortyDao() {
        return rickMortyDao;
    }

    public void setRickMortyDao(RickMortyDao rickMortyDao) {
        this.rickMortyDao = rickMortyDao;
    }

    public DatabaseCallback getCallback() {
        return callback;
    }

    public void setCallback(DatabaseCallback callback) {
        this.callback = callback;
    }
}
