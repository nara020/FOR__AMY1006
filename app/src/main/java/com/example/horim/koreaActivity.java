package com.example.horim;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class koreaActivity extends AppCompatActivity {
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
    Button _9;
    Button _10;
    Button _11;
    Button _12;
    Button _13;
    Button _14;
    Button _15;
    Button _16;
    Button _17;
    Button _18;
    Button _19;
    Button _20;
    Button _21;
    Button _22;
    Button _23;
    Button _24;
    Button _25;
    Button _26;
    Button _27, _28, _29, _30, _31, _32, _33, _34, _35, _36;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korea);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _1=(Button)findViewById(R.id._1);
        _1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(koreaActivity.this, korea_1.class);
                startActivity(intent);
            }
        });


    }
}
