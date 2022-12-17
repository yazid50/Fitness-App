package com.rifianduo.fitnessapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class frag_home extends Fragment implements onWorkOutClicked {

    private TextView txtUsername;
    private RecyclerView recyclerView;
    private ArrayList<workoutCategoryModel> modelArrayList;
    private workoutCategoryAdapter workoutCategoryAdapter;
    private final Context context;
    private final Activity activity;


    public frag_home(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_home, container, false);
        txtUsername = v.findViewById(R.id.welcomeUser);
        recyclerView = v.findViewById(R.id.list_fitness);
        txtUsername.setText("Welcome ".concat(prefUser.getInstance(context).getString(prefUser.username)));
        if (modelArrayList == null)
            modelArrayList = new ArrayList<>();

        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(context, R.drawable.abs), "ABS WORKOUT"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(context, R.drawable.arm), "ARM WORKOUT"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(context, R.drawable.chest), "CHEST WORKOUT"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(context, R.drawable.shoulder_and_back), "SHOULDER & BACK WORKOUT"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(context, R.drawable.leg), "LEG WORKOUT"));
        workoutCategoryAdapter = new workoutCategoryAdapter(modelArrayList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(workoutCategoryAdapter);
        return v;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(context, details_workout.class);
        intent.putExtra("workout", modelArrayList.get(position).getTextCategory());
        activity.startActivity(intent);
        activity.finish();
    }
}