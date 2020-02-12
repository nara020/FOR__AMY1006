package com.example.horim;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class menudetail extends AppCompatActivity   {

        private GoogleMap mMap;

        private WebView mWebView;
        private WebSettings mWebSettings;

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

    String title , date, path;
    TextView textView3, textView4;
    ImageView read_image;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menudetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//액션바 생성
//
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        Bundle bundle2 = getIntent().getBundleExtra("adapter");
//        if (bundle2 != null) {
//            title = bundle2.getCharSequence("asd").toString();
//            date = bundle2.getCharSequence("date").toString();
//
//        }
//        read_image = (ImageView)findViewById(R.id.read_image);
//        textView3 = (TextView)findViewById(R.id.textView3);
//        textView4 = (TextView)findViewById(R.id.textView4);
//        path = "http://192.168.1.178:8080/upload_data/data/202002/20200211180115491.jpg";
//
//
//        textView3.setText(title);
//        textView4.setText(date);
//        Glide.with(this).load(path).into(read_image);
    }
//
//        public void onMapReady(final GoogleMap googleMap) {
//
//            mMap = googleMap;
//
//            LatLng SEOUL = new LatLng(37.213586,126.975342);
//
//            MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.position(SEOUL)
//                    .title("닭발왕(Dakbarwang)")
//                    .snippet("night_food")
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//            mMap.addMarker(markerOptions);
//
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
//        }

}
