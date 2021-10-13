package com.rickmortis.UI.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.rickmortis.API.ApiClient;
import com.rickmortis.API.ApiInterface;
import com.rickmortis.Model.Character.DataCharacterApi;
import com.rickmortis.R;
import com.rickmortis.UI.Activity.MainActivity;
import com.rickmortis.UI.Adapter.CharacterAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CharacterFragment extends Fragment {
    private static final String TAG = "CharacterFragment";

    private MainActivity mainActivity;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private EditText serchCharacter;
    private ImageButton searchButton;
    private ApiInterface apiService;
    private int page;

    private String nameCharacter = "";


    public CharacterFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_character, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCharacter);
        serchCharacter = view.findViewById(R.id.et_name_character);
        searchButton = view.findViewById(R.id.search_button);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        page = 1;


        mainActivity = (MainActivity) getActivity();



        getCharacterCallback();

        setRecyclerViewScroll();
        search();

        return view;
    }

    public void search (){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!serchCharacter.getText().toString().isEmpty()){
                    nameCharacter = serchCharacter.getText().toString();
                    page = 1;
                    getCharacterFilter();
                }
            }
        });
    }

    //Scroll view
    public void setRecyclerViewScroll(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    if (!nameCharacter.equals("")){
                        getCharacterFilter();
                    }
                    else{
                        getCharacterCallback();
                    }

                }
            }
        });
    }

    private void getCharacterFilter() {
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

    //Request for charact with page
    public void getCharacterCallback(){
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


    //Init recycler view
    private void setRecyclerViewCharacter(DataCharacterApi dataCharacterApi){
        // Create adapter passing in the sample user data
        characterAdapter = new CharacterAdapter(dataCharacterApi.getCharacters(), mainActivity);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(characterAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}