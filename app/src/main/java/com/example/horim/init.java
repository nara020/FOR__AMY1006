package com.example.horim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class init extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init);

        try {

            startActivity(new Intent(init.this, MainActivity.class));
            finish();
        } catch (Exception e) {

        }
            //  overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            //finish();

    }
}