package com.rifianduo.fitnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class workoutCategoryAdapter extends RecyclerView.Adapter<workoutCategoryAdapter.ViewHolder> {

    private final ArrayList<workoutCategoryModel> modelArrayList;
    private final onWorkOutClicked onWorkOutClicked;

    public workoutCategoryAdapter(ArrayList<workoutCategoryModel> modelArrayList, onWorkOutClicked onWorkOutClicked) {
        this.modelArrayList = modelArrayList;
        this.onWorkOutClicked = onWorkOutClicked;
    }

    @NonNull
    @Override
    public workoutCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fitness, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull workoutCategoryAdapter.ViewHolder holder, int position) {
        workoutCategoryModel categoryModel = modelArrayList.get(position);
        holder.imgCategory.setBackground(categoryModel.getImageCategory());
        holder.textCategory.setText(categoryModel.getTextCategory());
        holder.imgCategory.setOnClickListener(view -> onWorkOutClicked.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return modelArrayList != null ? modelArrayList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout imgCategory;
        TextView textCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.img_workout_bg);
            textCategory = itemView.findViewById(R.id.typeWorkOut);
        }
    }
}
