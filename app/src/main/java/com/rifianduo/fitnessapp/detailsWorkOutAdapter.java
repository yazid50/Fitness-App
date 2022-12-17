package com.rifianduo.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class detailsWorkOutAdapter extends RecyclerView.Adapter<detailsWorkOutAdapter.ViewHolder> {

    private final ArrayList<workoutCategoryModel> modelArrayList;
    private final Context context;

    public detailsWorkOutAdapter(ArrayList<workoutCategoryModel> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public detailsWorkOutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout_details, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull detailsWorkOutAdapter.ViewHolder holder, int position) {
        workoutCategoryModel categoryModel = modelArrayList.get(position);
        holder.nameWorkOut.setText(categoryModel.getTextCategory());
        holder.workOutGif.setImageDrawable(categoryModel.getImageCategory());
    }

    @Override
    public int getItemCount() {
        return modelArrayList != null ? modelArrayList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        GifImageView workOutGif;
        TextView nameWorkOut;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workOutGif = itemView.findViewById(R.id.workOutGif);
            nameWorkOut = itemView.findViewById(R.id.nameWorkOut);
        }
    }
}
