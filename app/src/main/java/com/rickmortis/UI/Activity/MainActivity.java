package com.rickmortis.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.rickmortis.API.ApiClient;
import com.rickmortis.API.ApiInterface;
import com.rickmortis.Model.Character.Character;
import com.rickmortis.R;
import com.rickmortis.Tools.UserData;
import com.rickmortis.UI.Fragment.CharacterFragment;
import com.rickmortis.UI.Fragment.EpisodeFragment;
import com.rickmortis.UI.Fragment.LocationFragment;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    private static final String CHARACTER_FAV = "CharacterFav";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNavigation();
        loadCharacter();
        //loading the default fragment
        loadFragment(new CharacterFragment());
    }

    //Bottom navigation view
    private void setNavigation(){
        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setItemIconTintList(null);
        navigation.setSelectedItemId(R.id.action_character);
        navigation.setOnItemSelectedListener(this);
    }


    //Gestion des fragments
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_location:
                loadFragment(new LocationFragment());
                break;

            case R.id.action_character:
                loadFragment(new CharacterFragment());
                break;

            case R.id.action_episode:
                loadFragment(new EpisodeFragment());
                break;
        }
        return true;
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
        }
    }


    //Shared Preferences
    public void saveCharacter(){
        Gson gson = new Gson();

        SharedPreferences sharedPreferences = getSharedPreferences(CHARACTER_FAV, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Map<String, ?> allEntries = sharedPreferences.getAll();
        editor.clear();

        for (Character character : UserData.getInstance().getArrayFavCharac()) {
            if (!allEntries.isEmpty()){
                String json = gson.toJson(character);
                editor.putString(String.valueOf(character.getId()), json);
            }
            else {
                String json = gson.toJson(character);
                editor.putString(String.valueOf(character.getId()), json);
            }
        }
        editor.apply();
    }


    public void loadCharacter(){
        Gson gson = new Gson();

        SharedPreferences sharedPreferences = getSharedPreferences(CHARACTER_FAV, 0);
        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String json = sharedPreferences.getString(entry.getKey(),"");
            Character character = gson.fromJson(json, Character.class);
            UserData.getInstance().getArrayFavCharac().add(character);
        }
    }
}