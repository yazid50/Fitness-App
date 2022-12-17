package com.rifianduo.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class details_workout extends AppCompatActivity {

    private ImageView back;
    private LinearLayout imgWorkout;
    private TextView typeWorkout, numberWorkout;
    private RecyclerView recyclerView;
    private ArrayList<workoutCategoryModel> modelArrayList;
    private detailsWorkOutAdapter detailsWorkOutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_workout);
        back = findViewById(R.id.backImgDetails);
        imgWorkout = findViewById(R.id.img_workout_details);
        typeWorkout = findViewById(R.id.typeWorkOutDetails);
        numberWorkout = findViewById(R.id.numberWorkOut);
        recyclerView = findViewById(R.id.list_workout);

        getIntentData();
        if (modelArrayList == null)
            modelArrayList = new ArrayList<>();

        setAdapter();
        back.setOnClickListener(view -> utils.goAction(details_workout.this, details_workout.this, MainActivity.class));
    }

    private void setAdapter() {
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_1), "MOUNTAIN CLIMBER"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_2), "CROSSOVER CRUNCH"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_3), "TRICEPS DIPS"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_4), "BICYCLE CRUNCHES"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_6), "HEEL TOUCH"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_7), "ABDOMINAL CRUNCHES"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_9), "ONE LEG-UP"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_10), "PUSH-UP & ROTATION"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_11), "PLANK & LEG RAISES"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_12), "RUSSIAN TWIST"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_13), "BUTT BRIDGE"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_14), "V-UP"));
        modelArrayList.add(new workoutCategoryModel(ContextCompat.getDrawable(details_workout.this, R.drawable.exersice_15), "SPINE LUMBAR TWIST"));
        numberWorkout.setText(modelArrayList.size() + " WORKOUT");
        Collections.reverse(modelArrayList);
        detailsWorkOutAdapter = new detailsWorkOutAdapter(modelArrayList, details_workout.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(details_workout.this));
        recyclerView.setAdapter(detailsWorkOutAdapter);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent.getExtras() != null){
            String textCategory = intent.getStringExtra("workout");
            switch (textCategory) {
                case "ABS WORKOUT":
                    imgWorkout.setBackground(ContextCompat.getDrawable(details_workout.this, R.drawable.abs));
                    break;
                case "ARM WORKOUT":
                    imgWorkout.setBackground(ContextCompat.getDrawable(details_workout.this, R.drawable.arm));
                    break;
                case "CHEST WORKOUT":
                    imgWorkout.setBackground(ContextCompat.getDrawable(details_workout.this, R.drawable.chest));
                    break;
                case "SHOULDER & BACK WORKOUT":
                    imgWorkout.setBackground(ContextCompat.getDrawable(details_workout.this, R.drawable.shoulder_and_back));
                    break;
                default:
                    imgWorkout.setBackground(ContextCompat.getDrawable(details_workout.this, R.drawable.leg));
                    break;
            }
            typeWorkout.setText(textCategory);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        utils.goAction(details_workout.this, details_workout.this, MainActivity.class);
    }
}