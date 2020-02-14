package com.example.horim.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.horim.R;
import com.example.horim.adapters.MenuAdapter;
import com.example.horim.chickendetail;
import com.example.horim.models.MenuInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import static com.example.horim.chickendetail.mScroll_sv;

public class MenuFragment extends Fragment {
    ViewPager viewPager;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.menufragment,container,false);


        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        //  titleLayout = findViewById(R.id.titleLayout);



        출처: https://itpangpang.xyz/143 [ITPangPang]


        //
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.scrollToPosition(0);


//            ViewTreeObserver vto = titleLayout.getViewTreeObserver();
//            vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        titleLayout.getViewTreeObserver()
//                                .removeOnGlobalLayoutListener(this);
//                    } else {
//                        titleLayout.getViewTreeObserver()
//                                .removeGlobalOnLayoutListener(this);
//                    }
//                     x  = titleLayout.getX();
//                     y = titleLayout.getY();
//
//                    Log.d("MAIN",x+"           + x좌표입니다");
//                    Log.d("MAIN",y+"첫번쨰 입니다");
//
//                }
//            });
//
//
//            Log.d("MAIN",x+"           + x좌표입니다");
//            Log.d("MAIN",y+"5번쨰 입니다");
//

        mScroll_sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!mScroll_sv.canScrollVertically(1))
                {//최하단일때
                    mRecyclerView.setNestedScrollingEnabled(true);
                }
                else if(!mScroll_sv.canScrollVertically(-1))
                {//최상단일때
                    mRecyclerView.setNestedScrollingEnabled(false);

                }
                else if(!mRecyclerView.canScrollVertically(-1)) {
                    mRecyclerView.setNestedScrollingEnabled(false);
                }
                else if(!mRecyclerView.canScrollVertically(1)) {
                    mRecyclerView.setNestedScrollingEnabled(true);
                }
                else {
                    //idle?
                    mRecyclerView.setNestedScrollingEnabled(false);

                }

                return false;
            }
        });

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!mScroll_sv.canScrollVertically(1))
                {//최하단일때
                    mRecyclerView.setNestedScrollingEnabled(true);
                }
                else if(!mScroll_sv.canScrollVertically(-1))
                {//최상단일때
                    mRecyclerView.setNestedScrollingEnabled(false);

                }
                else if(!mRecyclerView.canScrollVertically(-1)) {
                    mRecyclerView.setNestedScrollingEnabled(false);
                }
                else if(!mRecyclerView.canScrollVertically(1)) {
                    mRecyclerView.setNestedScrollingEnabled(true);
                }
                else {
                    //idle?
                    mRecyclerView.setNestedScrollingEnabled(false);

                }

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
        MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.bread, "허니 브래드","빵 위에 꿀과 생크림 존나맛있겟지? 부럽지? 먹고싶지?","가격 10000원 ","알러지 성분 : 그딴거 없음 알고보니 있었ㅠ음 ㅠ 이것 근데 여기서 더 길어지면 다른 레이아웃들도 길어지는건가?  ","halal food","Spicy +++"));
        MenuInfoInfoArrayList.add(new MenuInfo(R.drawable.noodle, "이름 아니네 알아서 맞춰나오는구나","설명","가격","알러지","","매움"));
        MenuAdapter MenuAdapter = new MenuAdapter(getActivity()//컨텍스트를 보내줘서 어디서 어드로 갈지에 대해 정보를 줘야한다. this.activity로 사용하지 않기 때문에
                ,MenuInfoInfoArrayList);

        mRecyclerView.setAdapter(MenuAdapter);





        return rootView;

    }

}