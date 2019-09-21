package com.example.horim;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class nightActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    Button _1;
    Button _2;
    Button _3;
    Button _4;
    Button _5;
    Button _6;
    Button _7;
    Button _8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _1=(Button)findViewById(R.id._1);
        _1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nightActivity.this, ni_1.class);
                startActivity(intent);
            }
        });

        _2=(Button)findViewById(R.id._2);
        _2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nightActivity.this, ni_2.class);
                startActivity(intent);
            }
        });

        _3=(Button)findViewById(R.id._3);
        _3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nightActivity.this, ni_3.class);
                startActivity(intent);
            }
        });

        _4=(Button)findViewById(R.id._4);
        _4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nightActivity.this, ni_4.class);
                startActivity(intent);
            }
        });

        _5=(Button)findViewById(R.id._5);
        _5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nightActivity.this, ni_5.class);
                startActivity(intent);
            }
        });

        _6=(Button)findViewById(R.id._6);
        _6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nightActivity.this, ni_6.class);
                startActivity(intent);
            }
        });

        _7=(Button)findViewById(R.id._7);
        _7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nightActivity.this, ni_7.class);
                startActivity(intent);
            }
        });

        _8=(Button)findViewById(R.id._8);
        _8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nightActivity.this, ni_8.class);
                startActivity(intent);
            }
        });
    }
}
