package com.rifianduo.fitnessapp;

import android.os.Bundle;
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

public class SignUp extends AppCompatActivity {

    private EditText txtEmail,txtPassword,txtConfirmPassword,txtUsername;
    private TextView signin;
    private Button signup;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtEmail = findViewById(R.id.signupEmail);
        txtPassword = findViewById(R.id.signupPass);
        txtConfirmPassword = findViewById(R.id.signupCofirmPass);
        txtUsername = findViewById(R.id.signupUsername);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.goSignIn);

        signin.setOnClickListener(view -> utils.goAction(SignUp.this, SignUp.this, Login.class));

        signup.setOnClickListener(view -> {
            String username = txtUsername.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            String confirmPassword = txtConfirmPassword.getText().toString().trim();

            if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()){
                if (confirmPassword.equals(password)){
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        database = FirebaseDatabase.getInstance();
                        reference = database.getReference("users");
                        userModel userModel = new userModel(username, email, password);
                        Query checkUserDatabase = reference.orderByChild("username").equalTo(username);
                        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    Toasty.error(SignUp.this, "Username Already exits !", Toasty.LENGTH_SHORT).show();
                                }else {
                                    String checkEmail = snapshot.child(username).child("email").getValue(String.class);
                                    if (checkEmail != null && checkEmail.equals(email)) {
                                        Toasty.error(SignUp.this, "Email Already exits !", Toasty.LENGTH_SHORT).show();
                                    }else {
                                        reference.child(username).setValue(userModel);
                                        Toasty.success(SignUp.this, "Sign up successfully", Toasty.LENGTH_SHORT).show();
                                        utils.goAction(SignUp.this, SignUp.this, Login.class);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else {
                        Toasty.warning(SignUp.this, "Please enter valid email !", Toasty.LENGTH_SHORT).show();
                    }
                }else {
                    Toasty.warning(SignUp.this, "Confirm password dosn't match password", Toasty.LENGTH_SHORT).show();
                }
            }else {
                Toasty.warning(SignUp.this, "All fields are required!", Toasty.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        utils.goAction(SignUp.this, SignUp.this, welcome.class);
    }
}