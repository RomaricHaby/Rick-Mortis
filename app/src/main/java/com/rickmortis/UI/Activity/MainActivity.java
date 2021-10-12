package com.rickmortis.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rickmortis.R;
import com.rickmortis.UI.Fragment.CharacterFragment;
import com.rickmortis.UI.Fragment.EpisodeFragment;
import com.rickmortis.UI.Fragment.LocationFragment;



public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private MaterialToolbar toolbar;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.bottomNavigationView);
        navigation.setItemIconTintList(null);
        
        //loading the default fragment
        loadFragment(new CharacterFragment());
        navigation.setSelectedItemId(R.id.action_character);


        navigation.setOnNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.main_activity_toolbar);



    }

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
}