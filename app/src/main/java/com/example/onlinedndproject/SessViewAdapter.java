package com.example.onlinedndproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dnd_project_logic.entities.Session;

public class SessViewAdapter extends RecyclerView.Adapter<SessViewAdapter.ViewHolder> {
    public ArrayList<Session> sessions;
    private Context context;
    public RecyclerView RC;
    public int player_id;

    public SessViewAdapter(ArrayList<Session> sessions, Context context, int player_id) {
        this.sessions = sessions;
        this.context = context;
        this.player_id = player_id;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sess_adapter_view, parent, false);
        convertView.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View V) {
                                               /*int itemPosition = RC.getChildLayoutPosition(V);
                                               Session session = sessions.get(itemPosition);
                                               Intent intent = new Intent(context, NewSessActivity.class);
                                               intent.putExtra("session_id", session.getId());
                                               intent.putExtra("city", session.getCity());
                                               intent.putExtra("date", session.getStrDate());
                                               intent.putExtra("player_cappacity", session.getPlayer_capacity());
                                               intent.putExtra("fk_story_id", session.getFk_story_id());


                                               intent.putExtra("mode", 1);
                                               parent.getContext().startActivity(intent);*/

                                               int itemPosition = RC.getChildLayoutPosition(V);
                                               Session session = sessions.get(itemPosition);
                                               Intent intent = new Intent(context, CharOnSessActivity.class);
                                               intent.putExtra("session_id", session.getId());
                                               intent.putExtra("player_id", player_id);


                                               intent.putExtra("mode", 1);
                                               parent.getContext().startActivity(intent);


                                           }
                                       }
        );
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Session session = sessions.get(position);
        ArrayList<TextView> texts = new ArrayList<TextView>();
        for(int i=0; i<session.getPlayer_capacity()-1;i++){
            TextView text = new TextView(context);
            text.setText(String.valueOf("O"));
            text.setTextColor(Color.parseColor("#000000"));
            //text.setTextColor(Color.parseColor("#7FFFD4"));
            text.setTextSize(20);
            text.setPadding(5,20,0,0);
            texts.add(text);
        }
        for(int i=1; i<session.getPlayer_capacity();i++){
            TextView text = new TextView(context);
            text.setText(String.valueOf("O"));
            text.setTextColor(Color.parseColor("#CCCCCC"));
            text.setTextSize(20);
            text.setPadding(5,20,0,0);
            texts.add(text);
        }

        for(int i=0; i<session.getPlayer_capacity();i++){
            holder.fullbar.addView(texts.get(i));
            //holder.fullbar.add
        }

        holder.storyview.setText("Story: "+String.valueOf(session.getFk_story_id()));
        holder.dateview.setText(session.getStrDate());
        holder.cityview.setText(session.getCity());
    }

    @Override
    public int getItemCount() {
        return this.sessions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public View itemView;
        public LinearLayout fullbar;
        public TextView storyview;
        public TextView cityview;
        public TextView dateview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            fullbar = (LinearLayout) itemView.findViewById(R.id.fullbar);
            storyview = (TextView) itemView.findViewById(R.id.sessstoryview);
            cityview = (TextView) itemView.findViewById(R.id.sesscityview);
            dateview = (TextView) itemView.findViewById(R.id.sessdate);
        }
    }
}
