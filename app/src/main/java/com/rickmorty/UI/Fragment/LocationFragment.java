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
import com.rickmorty.Database.AsyncTasks.AddLocationsTask;
import com.rickmorty.Database.AsyncTasks.GetLocationsTask;
import com.rickmorty.Database.RequestDatabase;
import com.rickmorty.Model.Location.DataLocationApi;
import com.rickmorty.Model.Location.Locations;
import com.rickmorty.R;
import com.rickmorty.UI.Adapter.LocationAdapter;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFragment extends Fragment {
    private final String TAG ="LocationFragment";

    //API
    private ApiInterface apiService;
    private LocationAdapter locationAdapter;
    private int page = 1;
    private RecyclerView recyclerView;

    public LocationFragment() {}

    //Off line mode
    private static LocationFragment instance;
    private Boolean offLine = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_localisation, container, false);

        initUI(view);

        //Off line
        instance = this;

        // init API
        apiService = ApiClient.getClient().create(ApiInterface.class);

        //Call data API
        getLocationCallback();

        //set scroll with page
        setRecyclerViewScroll();

        return view;
    }

    private void initUI(View view) {
        this.recyclerView = view.findViewById(R.id.recyclerViewLocation);
    }

    //Init recycler view
    private void setRecyclerViewLocation(DataLocationApi dataLocationApi) {
        // Create adapter passing in the sample user data
        locationAdapter = new LocationAdapter(dataLocationApi.getLocations());
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(locationAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //Scroll view
    private void setRecyclerViewScroll(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!offLine){
                    if (!recyclerView.canScrollVertically(1)) {
                        getLocationCallback();

                    }
                }
            }
        });
    }

    //Request API
    private void getLocationCallback() {
        this.apiService.getLocation(page).enqueue(new Callback<DataLocationApi>() {
            @Override
            public void onResponse(Call<DataLocationApi> call, Response<DataLocationApi> response) {
                offLine = false;
                AddLocationsTask task = new AddLocationsTask();

                //Init recycler view
                if (page == 1){
                    if (response.body() != null) {
                        setRecyclerViewLocation(response.body());

                        task.execute(RequestDatabase.getInstance().getCallback(), response.body(), RequestDatabase.getInstance().getRickMortyDao());
                    }
                }
                else{
                    if (response.body() != null && page != response.body().getInfoLocation().getPages() + 1) {
                        locationAdapter.addData(response.body().getLocations());
                        locationAdapter.notifyDataSetChanged();

                        task.execute(RequestDatabase.getInstance().getCallback(), response.body(), RequestDatabase.getInstance().getRickMortyDao());
                    }
                }
                page++;
            }

            @Override
            public void onFailure(Call<DataLocationApi> call, Throwable t) {
                Log.e(TAG, "Throwable" + t);
                offLine = true;

                GetLocationsTask task = new GetLocationsTask();
                task.execute(RequestDatabase.getInstance().getCallback(),RequestDatabase.getInstance().getRickMortyDao());
            }
        });
    }

    //Off line mode
    public void setOffLineMode(List<Locations> locationsList){
        locationAdapter = new LocationAdapter(locationsList);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(locationAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public static LocationFragment getInstance() {
        return instance;
    }

}