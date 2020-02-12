package com.example.horim;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.horim.adapters.MenuAdapter;
import com.example.horim.adapters.MyAdapter;
import com.example.horim.models.FoodInfo;
import com.example.horim.models.MenuInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class chickendetail extends AppCompatActivity implements OnMapReadyCallback {

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
    ScrollView mScroll_sv;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chickendetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//액션바 생성


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle2 = getIntent().getBundleExtra("adapter");
        if (bundle2 != null) {
            title = bundle2.getCharSequence("asd").toString();
            date = bundle2.getCharSequence("date").toString();



            mRecyclerView = findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mScroll_sv = (ScrollView)findViewById(R.id.scroll_view);
            mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mScroll_sv.requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            });

            ArrayList<MenuInfo> MenuInfoInfoArrayList = new ArrayList<>();
            //여기서 값을 입력하면, 어뎁터로 넘어가서 리사이클링리스트뷰 형태로 생성시킨다. 즉 처음에 식당 정보들은 여기서 받고 어뎁터에서 리스트를만든 후 그걸 번들로 새 intent를 열어주면 완성
            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.strawberry, "이름","설명","가격","알러지","할랄","매움"));
            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.bread, "허니브래드","맛있음","4500원","알러지성분 그딴거없지","","안매워"));
            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","할랄","매움"));
            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","할랄","매움"));
            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","할랄","매움"));
            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","할랄","매움"));
            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","","매움"));
            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.bread, "허니 브래드","빵 위에 꿀과 생크림 존나맛있겟지? 부럽지? 먹고싶지?","가격","알러지 성분 : 그딴거 없음 알고보니 있었ㅠ음 ㅠ 이것  ","halal food","Spicy +++"));

            MenuAdapter MenuAdapter = new MenuAdapter(chickendetail.this//컨텍스트를 보내줘서 어디서 어드로 갈지에 대해 정보를 줘야한다. this.activity로 사용하지 않기 때문에
                    ,MenuInfoInfoArrayList);

            mRecyclerView.setAdapter(MenuAdapter);


        }
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

        public void onMapReady(final GoogleMap googleMap) {

            mMap = googleMap;

            LatLng SEOUL = new LatLng(37.213586,126.975342);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(SEOUL)
                    .title("닭발왕(Dakbarwang)")
                    .snippet("night_food")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }

}
