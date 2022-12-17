package com.rifianduo.fitnessapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class utils {

    public static AlertDialog dialog;

    public static void showAlertDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_dialog);
        dialog = builder.create();
        dialog.show();
    }

    public static void hideAlertDialog(){
        dialog.dismiss();
    }

    public static void goAction(Context context, @NonNull Activity activity, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        activity.startActivity(intent);
        activity.finish();
    }
}
