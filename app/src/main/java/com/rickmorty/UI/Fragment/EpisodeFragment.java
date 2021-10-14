package com.rickmorty.UI.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rickmorty.API.ApiClient;
import com.rickmorty.API.ApiInterface;
import com.rickmorty.Model.Episode.DataEpisodeApi;
import com.rickmorty.R;
import com.rickmorty.UI.Adapter.EpisodeAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EpisodeFragment extends Fragment {
    private final String TAG ="EpisodeFragment";

    //API
    private ApiInterface apiService;
    private EpisodeAdapter episodeAdapter;
    private int page = 1;
    private RecyclerView recyclerView;

    public EpisodeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_episode, container, false);

        initUI(view);

        // init API
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //Call data API
        getEpisodeCallback();

        //set scroll with page
        setRecyclerViewScroll();

        return view;
    }

    private void initUI(View view) {
        this.recyclerView = view.findViewById(R.id.recyclerViewEpisode);
    }

    //Init recycler view
    private void setRecyclerViewLocation(DataEpisodeApi dataEpisodeApi) {
        // Create adapter passing in the sample user data
        episodeAdapter = new EpisodeAdapter(dataEpisodeApi.getResults());
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(episodeAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //Scroll view
    private void setRecyclerViewScroll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    getEpisodeCallback();
                }
            }
        });
    }

    //Request API
    private void getEpisodeCallback() {
        this.apiService.getEpisode(page).enqueue(new Callback<DataEpisodeApi>() {
            @Override
            public void onResponse(Call<DataEpisodeApi> call, Response<DataEpisodeApi> response) {
                //Init recycler view
                if (page == 1){
                    if (response.body() != null) {
                        setRecyclerViewLocation(response.body());
                    }
                }
                else{
                    if (response.body() != null && page != response.body().getInfo().getPages() + 1) {
                        episodeAdapter.addData(response.body().getResults());
                        episodeAdapter.notifyDataSetChanged();
                    }
                }
                page++;
            }

            @Override
            public void onFailure(Call<DataEpisodeApi> call, Throwable t) {
                Log.e(TAG, "Throwable" + t);
            }
        });
    }


}