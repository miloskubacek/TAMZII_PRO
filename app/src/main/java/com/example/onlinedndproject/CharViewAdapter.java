package com.example.onlinedndproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import dnd_project_logic.entities.Character;

public class CharViewAdapter extends ArrayAdapter<Character> {
    private final Context context;
    private final int resource;

    public CharViewAdapter(Context context, int textViewResourceId, ArrayList<Character> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.resource = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String level = String.valueOf(getItem(position).getLevel());
        String prof_id = String.valueOf(getItem(position).getFk_prof_id());
        String race_id = String.valueOf(getItem(position).getFk_race_id());

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.charnameview);
        TextView tvLevel = (TextView) convertView.findViewById(R.id.charlevelview);
        TextView tvProf = (TextView) convertView.findViewById(R.id.charprofview);
        TextView tvRace= (TextView) convertView.findViewById(R.id.charraceview);

        tvName.setText(name);
        tvLevel.setText("Level: "+level);
        tvProf.setText(prof_id);
        tvRace.setText(race_id);

        return convertView;
    }
}
