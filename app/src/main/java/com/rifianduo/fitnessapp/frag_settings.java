package com.rifianduo.fitnessapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class frag_settings extends Fragment {

    private TextView usernameProfile,usernameInfo,emailInfo,passwordInfo;
    private EditText txtEmail,txtPassword;
    private Button save,edit,cancel,logout;
    private LinearLayout lnInfoSaver;
    private final Context context;
    private final Activity activity;
    private DatabaseReference reference;
    private String username;

    public frag_settings(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_settings, container, false);
        initViews(v);
        edit.setOnClickListener(view -> {
            editAction(true);
        });

        save.setOnClickListener(view -> checkUserInfoAndSave());
        cancel.setOnClickListener(view -> editAction(false));
        logout.setOnClickListener(view -> {
            prefUser.getInstance(context).clearPrefUser();
            utils.goAction(context,activity, welcome.class);
        });

        return v;
    }

    private void editAction(boolean isEdit) {
        if (isEdit){
            txtPassword.setVisibility(View.VISIBLE);
            txtEmail.setVisibility(View.VISIBLE);
            lnInfoSaver.setVisibility(View.VISIBLE);
            passwordInfo.setVisibility(View.GONE);
            emailInfo.setVisibility(View.GONE);
            edit.setVisibility(View.GONE);
        }else {
            txtPassword.setVisibility(View.GONE);
            txtEmail.setVisibility(View.GONE);
            lnInfoSaver.setVisibility(View.GONE);
            passwordInfo.setVisibility(View.VISIBLE);
            emailInfo.setVisibility(View.VISIBLE);
            edit.setVisibility(View.VISIBLE);
        }
        getUserInfos();
    }

    private void checkUserInfoAndSave() {
        String newEmail = txtEmail.getText().toString().trim();
        String newPassword = txtPassword.getText().toString().trim();

        if (!newEmail.isEmpty() && !newPassword.isEmpty()){
            reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUserDatabase = reference.orderByChild("username").equalTo(username);
            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String checkEmail = snapshot.child(username).child("email").getValue(String.class);
                        if (checkEmail != null && checkEmail.equals(newEmail)) {
                            Toasty.error(context, "Email Already exits !", Toasty.LENGTH_SHORT).show();
                        }else {
                            reference.child(username).child("email").setValue(newEmail);
                            reference.child(username).child("password").setValue(newPassword);
                            Toasty.success(context, "Infos has been saved", Toasty.LENGTH_SHORT).show();
                            prefUser.getInstance(context).putString(prefUser.email, newEmail);
                            editAction(false);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {
            Toasty.warning(context, "All fields are required !", Toasty.LENGTH_SHORT).show();
        }
    }

    private void initViews(@NonNull View v) {
        usernameProfile = v.findViewById(R.id.profileUsername);
        txtEmail = v.findViewById(R.id.editTxtEmail);
        txtPassword = v.findViewById(R.id.editTxtPass);
        edit = v.findViewById(R.id.btnEditInfo);
        save = v.findViewById(R.id.btnSaveInfo);
        cancel = v.findViewById(R.id.btnCancelInfo);
        lnInfoSaver = v.findViewById(R.id.lnInfoSaver);
        logout = v.findViewById(R.id.logout);
        usernameInfo = v.findViewById(R.id.txtUsernameInfo);
        emailInfo = v.findViewById(R.id.txtEmailInfo);
        passwordInfo = v.findViewById(R.id.txtPassInfo);
        getUserInfos();
    }

    private void getUserInfos() {
        username = prefUser.getInstance(context).getString(prefUser.username);
        usernameProfile.setText(username);
        usernameInfo.setText(username);
        emailInfo.setText(prefUser.getInstance(context).getString(prefUser.email));
    }
}