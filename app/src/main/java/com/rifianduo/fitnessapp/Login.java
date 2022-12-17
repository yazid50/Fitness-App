package com.rifianduo.fitnessapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {

    private EditText txtUsername,txtPassword;
    private TextView signup,forgetPassword;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.loginUsername);
        txtPassword = findViewById(R.id.loginPass);
        signup = findViewById(R.id.goSignUp);
        forgetPassword = findViewById(R.id.forgetPass);
        login = findViewById(R.id.login);

        login.setOnClickListener(view -> {
            String username = txtUsername.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase = reference.orderByChild("username").equalTo(username);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String passwordDB = snapshot.child(username).child("password").getValue(String.class);
                            if (passwordDB != null && passwordDB.equals(password)) {
                                prefUser.getInstance(Login.this).putString(prefUser.username, snapshot.child(username).child("username").getValue(String.class));
                                prefUser.getInstance(Login.this).putString(prefUser.email, snapshot.child(username).child("email").getValue(String.class));
                                prefUser.getInstance(Login.this).putBoolean(prefUser.isUserLogin, true);
                                Toasty.success(Login.this, "Login success", Toasty.LENGTH_SHORT).show();
                                utils.goAction(Login.this, Login.this, MainActivity.class);
                            }else {
                                Toasty.error(Login.this, "Invalid Credentials", Toasty.LENGTH_SHORT).show();
                            }
                        }else {
                            Toasty.warning(Login.this, "User does not exist", Toasty.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }else {
                Toasty.warning(Login.this, "All fields are required!", Toasty.LENGTH_SHORT).show();
            }
        });

        signup.setOnClickListener(view -> utils.goAction(Login.this, Login.this, SignUp.class));

        forgetPassword.setOnClickListener(view -> {

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        utils.goAction(Login.this, Login.this, welcome.class);
    }
}