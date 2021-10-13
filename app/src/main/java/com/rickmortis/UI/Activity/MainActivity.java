package com.rickmortis.UI.Activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.rickmortis.Model.Character.Character;
import com.rickmortis.R;
import com.rickmortis.Tools.UserData;
import com.rickmortis.UI.Fragment.CharacterFragment;
import com.rickmortis.UI.Fragment.EpisodeFragment;
import com.rickmortis.UI.Fragment.FavoritesFragment;
import com.rickmortis.UI.Fragment.LocationFragment;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    private static final String CHARACTER_FAV = "CharacterFav";

    private ImageButton favFragment;
    private  BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setNavigation();
        loadCharacter();
        //loading the default fragment
        loadFragment(new CharacterFragment());
    }

    private void initUI(){
        favFragment = findViewById(R.id.favButton);
        navigation = findViewById(R.id.bottomNavigationView);

        favButton();
    }

    private void favButton(){
        favFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.setSelectedItemId(R.id.action_character);
                loadFragment(new FavoritesFragment());
            }
        });
    }

    //Bottom navigation view
    private void setNavigation(){
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

    //Firebase gestion

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}