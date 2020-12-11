package com.example.onlinedndproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dnd_project_logic.entities.Character;

public class CharViewAdapter extends RecyclerView.Adapter<CharViewAdapter.ViewHolder> {
    public ArrayList<Character> characters;
    private Context context;
    public RecyclerView RC;

    public CharViewAdapter(ArrayList<Character> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_adapter_view, parent, false);
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                int itemPosition = RC.getChildLayoutPosition(V);
                Character character = characters.get(itemPosition);
                Intent intent = new Intent(context, NewCharActivity.class);
                intent.putExtra("char_id", character.getId());
                intent.putExtra("name", character.getName());
                intent.putExtra("fk_player_id", character.getFk_player_id());
                intent.putExtra("fk_race_id", character.getFk_race_id());
                intent.putExtra("fk_prof_id", character.getFk_prof_id());
                intent.putExtra("attack", character.getAttack());
                intent.putExtra("health", character.getHealth());
                intent.putExtra("deffence", character.getDeffence());
                intent.putExtra("level", character.getLevel());
                intent.putExtra("backstory", character.getBackstory());

                intent.putExtra("mode", 1);
                parent.getContext().startActivity(intent);
            }
        }
        );
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.tvName.setText(character.getName());
        holder.tvLevel.setText("Level: "+String.valueOf(character.getLevel()));
        holder.tvProf.setText(String.valueOf(character.getFk_prof_id()));
        holder.tvRace.setText(String.valueOf(character.getFk_race_id()));
    }

    @Override
    public int getItemCount() {
        return this.characters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public TextView tvLevel;
        public TextView tvProf;
        public TextView tvRace;
        public View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = (TextView) itemView.findViewById(R.id.charnameview);
            tvLevel = (TextView) itemView.findViewById(R.id.charlevelview);
            tvProf = (TextView) itemView.findViewById(R.id.charprofview);
            tvRace= (TextView) itemView.findViewById(R.id.charraceview);

        }
    }
}
