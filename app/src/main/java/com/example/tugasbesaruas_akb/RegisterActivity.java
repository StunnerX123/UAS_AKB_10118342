package com.example.tugasbesaruas_akb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText email, psd;
    Button simpan;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.edt_email);
        psd = findViewById(R.id.edt_psd);
        simpan = findViewById(R.id.btn_simpan);

        firebaseAuth = FirebaseAuth.getInstance();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt_email = email.getText().toString().trim();
                String edt_psd = psd.getText().toString().trim();

                if(TextUtils.isEmpty(edt_email)){
                    email.setError("Email is Empty");
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(edt_email).matches()){
                    email.setError("Please Enter Valid Email");
                }
                if(TextUtils.isEmpty(edt_psd)){
                    psd.setError("Password is Empty");
                }
                if(edt_psd.length() < 6){
                    psd.setError("Password Must be >= 6 Character");
                }

                firebaseAuth.createUserWithEmailAndPassword(edt_email, edt_psd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Akun Terdaftar", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        super.finish();
        super.onBackPressed();
    }
    //12-08-2021, 10118342-Muhammmad Abdurrohman-IF8
}