package com.rickmortis.UI.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rickmortis.Model.Character.Character;
import com.rickmortis.R;
import com.rickmortis.Tools.UserData;
import com.rickmortis.UI.Activity.MainActivity;
import com.rickmortis.UI.Adapter.ViewHolder.CharacterViewHolder;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder>{

    private final List<Character> characterList;
    private MainActivity activity;

    public CharacterAdapter(List<Character> characterList, MainActivity activity) {
        this.characterList = characterList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_holder_character, parent, false);

        return new CharacterViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        viewHolder.updateCharacter(this.characterList.get(position));


        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Character character = characterList.get(position);
                Log.e("CharacterAdapter", character.toString());
                if (UserData.getInstance().isFav(character)){
                    UserData.getInstance().removeFavCharacter(character);
                }
                else{
                    UserData.getInstance().addFavCharac(character);
                    activity.saveCharacter();
                }

                 viewHolder.updateFav(character);
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void  addData(List<Character> list){
        characterList.addAll(list);
    }

    public List<Character> getCharacterList() {
        return characterList;
    }
}