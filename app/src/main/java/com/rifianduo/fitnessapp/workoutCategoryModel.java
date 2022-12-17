package com.rifianduo.fitnessapp;

import android.graphics.drawable.Drawable;

public class workoutCategoryModel {
    public Drawable imageCategory;
    public String textCategory;

    public workoutCategoryModel(Drawable imageCategory, String textCategory) {
        this.imageCategory = imageCategory;
        this.textCategory = textCategory;
    }

    public Drawable getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(Drawable imageCategory) {
        this.imageCategory = imageCategory;
    }

    public String getTextCategory() {
        return textCategory;
    }

    public void setTextCategory(String textCategory) {
        this.textCategory = textCategory;
    }
}
