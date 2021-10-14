package com.rickmorty.UI.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rickmorty.API.ApiClient;
import com.rickmorty.API.ApiInterface;
import com.rickmorty.Model.Character.DataCharacterApi;
import com.rickmorty.R;
import com.rickmorty.UI.Activity.MainActivity;
import com.rickmorty.UI.Adapter.CharacterAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterFragment extends Fragment {
    private static final String TAG = "CharacterFragment";
    private MainActivity mainActivity;

    //API data
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private ApiInterface apiService;
    private int page = 1;

    //System search
    private EditText searchCharacter;
    private ImageButton searchButton;
    private String nameCharacter = "";

    public CharacterFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character, container, false);

        mainActivity = (MainActivity) getActivity();
        initUI(view);

        // init API
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //Call data API
        getCharacterCallback();

        //set scroll with page
        setRecyclerViewScroll();

        //set search system
        searchCharacter();

        return view;
    }

    private void initUI(View view){
        recyclerView = view.findViewById(R.id.recyclerViewCharacter);
        searchCharacter = view.findViewById(R.id.et_name_character);
        searchButton = view.findViewById(R.id.search_button);
    }

    //Init recycler view
    private void setRecyclerViewCharacter(DataCharacterApi dataCharacterApi){
        // Create adapter passing in the sample user data
        characterAdapter = new CharacterAdapter(dataCharacterApi.getCharacters(), mainActivity);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(characterAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    // Button search character
    private void searchCharacter(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!searchCharacter.getText().toString().isEmpty()){
                    nameCharacter = searchCharacter.getText().toString();
                    page = 1;
                    getCharacterFilterCallback();
                }
            }
        });
    }

    //Scroll view
    private void setRecyclerViewScroll(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    if (!nameCharacter.equals("")){
                        getCharacterFilterCallback();
                    }
                    else{
                        getCharacterCallback();
                    }

                }
            }
        });
    }

    //Request API
    private void getCharacterFilterCallback() {
        this.apiService.getCharacterFilter(nameCharacter).enqueue(new Callback<DataCharacterApi>() {
            @Override
            public void onResponse(Call<DataCharacterApi> call, Response<DataCharacterApi> response) {
                //Init recycler view
                if (page == 1){
                    if (response.body() != null) {
                        setRecyclerViewCharacter(response.body());
                    }
                }
                else{
                    if (response.body() != null && response.body().getInfo().getNext() != null) {
                        characterAdapter.addData(response.body().getCharacters());
                        characterAdapter.notifyDataSetChanged();
                    }
                }
                page++;
            }

            @Override
            public void onFailure(Call<DataCharacterApi> call, Throwable t) {

            }
        });
    }
    private void getCharacterCallback(){
        this.apiService.getCharacter(page).enqueue(new Callback<DataCharacterApi>() {
            @Override
            public void onResponse(Call<DataCharacterApi> call, Response<DataCharacterApi> response) {
                //Init recycler view
                if (page == 1){
                    if (response.body() != null) {
                        setRecyclerViewCharacter(response.body());
                    }
                }
                else{
                    if (response.body() != null && page != response.body().getInfo().getPages() + 1) {
                       characterAdapter.addData(response.body().getCharacters());
                       characterAdapter.notifyDataSetChanged();

                    }
                }
                page++;
            }

            @Override
            public void onFailure(Call<DataCharacterApi> call, Throwable t) {
                Log.e(TAG, "Throwable" + t);
            }
        });
    }
}