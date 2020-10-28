package com.example.fitnessproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

public class ExeAdaptor extends RecyclerView.Adapter<ExeAdaptor.viewHolder> {

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView exeName;
      //  ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            exeName = (TextView) itemView.findViewById(R.id.exeName);
           // imageView = (ImageView) itemView.findViewById(R.id.imageDisplay);
        }
    }



    private ArrayList<ExeDatatype> list ;

    public ExeAdaptor(ArrayList<ExeDatatype> horizontalList)
    {
        this.list = horizontalList;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exe_holder,
                        parent,
                        false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.exeName.setText( list.get(position).getExeName());
      //  holder.imageView.setImageResource(list.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
