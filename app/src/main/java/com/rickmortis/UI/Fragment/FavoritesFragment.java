package com.rickmortis.UI.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rickmortis.R;
import com.rickmortis.Tools.UserData;
import com.rickmortis.UI.Activity.MainActivity;
import com.rickmortis.UI.Adapter.CharacterAdapter;

public class FavoritesFragment extends Fragment {
    private final String TAG ="FavoritesFragment";

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private MainActivity mainActivity;

    public FavoritesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        mainActivity = (MainActivity) getActivity();

        initUI(view);

        setRecyclerViewFav();
        return view;
    }

    private void initUI(View view) {
        this.recyclerView = view.findViewById(R.id.recyclerViewFav);
    }

    //Init recycler view
    private void setRecyclerViewFav() {
        // Create adapter passing in the sample user data
        characterAdapter = new CharacterAdapter(UserData.getInstance().getArrayFavCharac(), mainActivity);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(characterAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
