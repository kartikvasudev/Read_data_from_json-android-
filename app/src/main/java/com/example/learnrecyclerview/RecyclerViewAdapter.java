package com.example.learnrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {
    private ArrayList<HashMap<String,String>> arrayList;
    public RecyclerViewAdapter(ArrayList<HashMap<String,String>> arrayList) {
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,null);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        HashMap<String,String> hashMap=new HashMap<>();
        holder.song_name.setText(arrayList.get(position).get("title"));
        holder.duration.setText(arrayList.get(position).get("duration"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView song_name,duration;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            song_name=itemView.findViewById(R.id.song_name);
            duration=itemView.findViewById(R.id.duration);
        }
    }
}
