package com.example.tugasbesaruas_akb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    ImageButton eyeToggle;
    Button login,daftarAkun;
    FirebaseAuth firebaseAuth;

    boolean show=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.edt_username);
        password=findViewById(R.id.edt_password);
        eyeToggle=findViewById(R.id.toggle_password);
        login=findViewById(R.id.btn_login);
        daftarAkun=findViewById(R.id.btn_daftar_akun);

        firebaseAuth = FirebaseAuth.getInstance();

        daftarAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });

        eyeToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show)
                {
                    show=false;
                    eyeToggle.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    password.setTransformationMethod(new PasswordTransformationMethod());
                }
                else
                {
                    show=true;
                    eyeToggle.setImageResource(R.drawable.ic_baseline_visibility_24);
                    password.setTransformationMethod(null);
                }
            }
        });

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    username.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.edit_text_focus_bg));
                    password.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.edit_text_bg));
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                {
                    password.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.edit_text_focus_bg));
                    username.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.edit_text_bg));
                }
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(password.getText()) && !TextUtils.isEmpty(s))
                {
                    login.setEnabled(true);
                    login.setClickable(true);
                    login.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    login.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.btn_bg_active));
                }
                else
                {
                    login.setEnabled(false);
                    login.setClickable(false);
                    login.setTextColor(getResources().getColor(R.color.grey));
                    login.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.bg_btn));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(s))
                {
                    login.setEnabled(true);
                    login.setClickable(true);
                    login.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    login.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.btn_bg_active));
                }
                else
                {
                    login.setEnabled(false);
                    login.setClickable(false);
                    login.setTextColor(getResources().getColor(R.color.grey));
                    login.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.bg_btn));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String psd = password.getText().toString().trim();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(psd)){
                    username.setError("Email or Password is Empty");
                    password.setError("Email or Password is Empty");
                    return;
                }
                if(psd.length() < 6){
                    password.setError("Password Must be >= 6 Character");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, psd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    //12-08-2021, 10118342-Muhammmad Abdurrohman-IF8
}