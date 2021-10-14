package com.rickmorty.Database.AsyncTasks;

import com.rickmorty.UI.Activity.MainActivity;
import java.util.List;
import javax.security.auth.callback.Callback;

public class DatabaseCallback implements Callback {

    private MainActivity parent;

    public DatabaseCallback(MainActivity parent) {
        this.parent = parent;
    }

    public void onResponse(){

    }

    public void onResponse(List<?> data){
        parent.onResponse(data);
    }
}
