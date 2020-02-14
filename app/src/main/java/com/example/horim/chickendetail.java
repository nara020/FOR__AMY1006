package com.example.horim;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.horim.adapters.PagerAdapter;
import com.example.horim.fragments.InfoFragment;
import com.example.horim.fragments.MenuFragment;
import com.example.horim.fragments.ReviewFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class chickendetail extends AppCompatActivity implements OnMapReadyCallback {

        private GoogleMap mMap;

    float x;
    float y;

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

    RatingBar ratingBar;
    String title , date, path;
    ImageView read_image;
    public static ScrollView mScroll_sv;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ViewPager mViewPager;
    InfoFragment infoFragment ;
    MenuFragment menuFragment ;
    ReviewFragment reviewFragment ;

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chickendetail);
        infoFragment = new InfoFragment();
        menuFragment = new MenuFragment();
        reviewFragment = new ReviewFragment();
//최초 시작 프레그먼트 지정 (맨처음에 몇번을 보여줄지
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,menuFragment).commit();
        //탭 레이아웃 호출 후 탭 추가

        TabLayout tabs = (TabLayout) findViewById(R.id.tab_layout);
        tabs.addTab(tabs.newTab().setText("메뉴"));
        tabs.addTab(tabs.newTab().setText("가게 정보"));
        tabs.addTab(tabs.newTab().setText("리뷰"));
        //각 탭을 누를 때 메소드 호출
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //몇번째 탭을 선택했는지 가져옴
                int position = tab.getPosition();

                //탭을 선택 한것에 따라 프래그먼트를 바꾼다.
                Fragment selected = null;
                if(position == 0){
                    selected = menuFragment;
                }
                else if(position == 1){
                    selected = infoFragment;
                }
                else if(position == 2){
                    selected = reviewFragment;
                }

                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,
                        R.anim.fade_out)
                        .replace(R.id.container,selected).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });










        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//액션바 생성


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle2 = getIntent().getBundleExtra("adapter");
        if (bundle2 != null) {
            title = bundle2.getCharSequence("asd").toString();
            date = bundle2.getCharSequence("date").toString();



//            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//            mRecyclerView.setHasFixedSize(true);
//            mLayoutManager = new LinearLayoutManager(this);
//            mRecyclerView.setLayoutManager(mLayoutManager);
          //  titleLayout = findViewById(R.id.titleLayout);
            mScroll_sv = (ScrollView)findViewById(R.id.scroll_view);

//            mRecyclerView.setNestedScrollingEnabled(false);
//
//
////            ViewTreeObserver vto = titleLayout.getViewTreeObserver();
////            vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
////                @Override
////                public void onGlobalLayout() {
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
////                        titleLayout.getViewTreeObserver()
////                                .removeOnGlobalLayoutListener(this);
////                    } else {
////                        titleLayout.getViewTreeObserver()
////                                .removeGlobalOnLayoutListener(this);
////                    }
////                     x  = titleLayout.getX();
////                     y = titleLayout.getY();
////
////                    Log.d("MAIN",x+"           + x좌표입니다");
////                    Log.d("MAIN",y+"첫번쨰 입니다");
////
////                }
////            });
////
////
////            Log.d("MAIN",x+"           + x좌표입니다");
////            Log.d("MAIN",y+"5번쨰 입니다");

//            mScroll_sv.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if(!mScroll_sv.canScrollVertically(1))
//                    {//최하단일때
//                        mRecyclerView.setNestedScrollingEnabled(true);
//                    }
//                    else if(!mScroll_sv.canScrollVertically(-1))
//                    {//최상단일때
//                        mRecyclerView.setNestedScrollingEnabled(false);
//
//                    }
//                    else if(!mRecyclerView.canScrollVertically(-1)) {
//                        mRecyclerView.setNestedScrollingEnabled(false);
//                    }
//                    else if(!mRecyclerView.canScrollVertically(1)) {
//                        mRecyclerView.setNestedScrollingEnabled(true);
//                    }
//                    else {
//                        //idle?
//                        mRecyclerView.setNestedScrollingEnabled(false);
//
//                    }
//
//                    return false;
//                }
//            });
//            mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if(!mScroll_sv.canScrollVertically(1))
//                    {//최하단일때
//                        mRecyclerView.setNestedScrollingEnabled(true);
//                    }
//                    else if(!mScroll_sv.canScrollVertically(-1))
//                    {//최상단일때
//                        mRecyclerView.setNestedScrollingEnabled(false);
//
//                    }
//                    else if(!mRecyclerView.canScrollVertically(-1)) {
//                        mRecyclerView.setNestedScrollingEnabled(false);
//                    }
//                    else if(!mRecyclerView.canScrollVertically(1)) {
//                        mRecyclerView.setNestedScrollingEnabled(true);
//                    }
//                    else {
//                        //idle?
//                        mRecyclerView.setNestedScrollingEnabled(false);
//
//                    }
//
//                    return false;
//                }
//            });
//
//            ArrayList<MenuInfo> MenuInfoInfoArrayList = new ArrayList<>();
//            //여기서 값을 입력하면, 어뎁터로 넘어가서 리사이클링리스트뷰 형태로 생성시킨다. 즉 처음에 식당 정보들은 여기서 받고 어뎁터에서 리스트를만든 후 그걸 번들로 새 intent를 열어주면 완성
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.strawberry, "이름","설명","가격","알러지","할랄","매움"));
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.bread, "허니브래드","맛있음","4500원","알러지성분 그딴거없지","","안매워"));
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","할랄","매움"));
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","할랄","매움"));
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","할랄","매움"));
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","할랄","매움"));
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름","설명","가격","알러지","","매움"));
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.bread, "허니 브래드","빵 위에 꿀과 생크림 존나맛있겟지? 부럽지? 먹고싶지?","가격 10000원 ","알러지 성분 : 그딴거 없음 알고보니 있었ㅠ음 ㅠ 이것 근데 여기서 더 길어지면 다른 레이아웃들도 길어지는건가?  ","halal food","Spicy +++"));
//            MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름 아니네 알아서 맞춰나오는구나","설명","가격","알러지","","매움"));
//            MenuAdapter MenuAdapter = new MenuAdapter(chickendetail.this//컨텍스트를 보내줘서 어디서 어드로 갈지에 대해 정보를 줘야한다. this.activity로 사용하지 않기 때문에
//                    ,MenuInfoInfoArrayList);
//
//            mRecyclerView.setAdapter(MenuAdapter);


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






//---------------------------------------------------------------아래로 레이팅바
//        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//
//            @Override
//
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//
//                // 저는 0개를 주기싫어서, 만약 1개미만이면 강제로 1개를 넣었습니다.
//
//                if (ratingBar.getRating()<1.0f){
//
//                    ratingBar.setRating(1);
//
//                }
//
//            }
//
//        });



    }





       // ------------------------------------------------------여기까지

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
