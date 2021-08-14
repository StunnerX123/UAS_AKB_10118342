package com.example.tugasbesaruas_akb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.map){
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        }else if(item.getItemId()==R.id.profil){
            startActivity(new Intent(this, WisataActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        }else if(item.getItemId()==R.id.about){
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, WisataActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.finish();
        super.onBackPressed();
    }
    //12-08-2021, 10118342-Muhammmad Abdurrohman-IF8
}