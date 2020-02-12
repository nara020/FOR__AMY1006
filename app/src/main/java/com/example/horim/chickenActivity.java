package com.example.horim;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.horim.adapters.MyAdapter;
import com.example.horim.models.FoodInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class chickenActivity extends AppCompatActivity {

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

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//액션바 생성

                mRecyclerView = findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                ArrayList<FoodInfo> chcikenInfoArrayList = new ArrayList<>();
                //여기서 값을 입력하면, 어뎁터로 넘어가서 리사이클링리스트뷰 형태로 생성시킨다. 즉 처음에 식당 정보들은 여기서 받고 어뎁터에서 리스트를만든 후 그걸 번들로 새 intent를 열어주면 완성
        chcikenInfoArrayList.add(new FoodInfo(R.drawable.strawberry, "딸기파는 가계"));
        chcikenInfoArrayList.add(new FoodInfo(R.drawable.bread, "커피집입니다"));
        chcikenInfoArrayList.add(new FoodInfo(R.drawable.noodle, "짜장면 집이네 "));

                MyAdapter myAdapter = new MyAdapter(chickenActivity.this//컨텍스트를 보내줘서 어디서 어드로 갈지에 대해 정보를 줘야한다. this.activity로 사용하지 않기 때문에
                        ,chcikenInfoArrayList);

                mRecyclerView.setAdapter(myAdapter);

            }
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        //리사이클러뷰는 리스트뷰처럼 단순 껍데기일뿐이다. 어댑터를 만들어 관리 설정해줘야한다.
//        recyclerView = findViewById(R.id.recyclerView);
//
//        //inearLayoutManager.HORIZONTAL로 넘기는 방향설정 가능 (첫번쨰파라미터는 context 세번쨰파라미터는 아이템이 보이는 방향을 애기한다.)
//        //세번쨰파라미터는 예를들어 채팅방같은 경우 메세지가 아래에서 위로 올라가는 방향같은거 설정
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false); //레이아웃매니저 생성
//
//        recyclerView.setLayoutManager(layoutManager);//만든 레이아웃매니저 객체를(설정을) 리사이클러 뷰에 설정해줌
//
//        adapter = new SingerAdapter(getApplicationContext());
//        //아이템추가
//        adapter.addItem(new SingerItem("조용필", "010-1111-2222"));
//        adapter.addItem(new SingerItem("디셈버", "010-3333-4444"));
//        adapter.addItem(new SingerItem("마마무", "010-5555-3333"));
//        adapter.addItem(new SingerItem("박효신", "010-4444-8888"));
//        adapter.addItem(new SingerItem("태진아", "010-0000-9999"));
//
//        //어댑터에 연결
//        recyclerView.setAdapter(adapter);
//
//        //어댑터클래스에 직접 이벤트처리관련 코드를 작성해줘야함 (리스트뷰처럼 구현되어있지않음 직접 정의해놔야한다.)
//        //setOnItemClickListener라는 이름으로 이벤트 메소드 직접 정의한거임
//        adapter.setOnItemClickListener(new SingerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position) {
//                SingerItem item = adapter.getItem(position);
//                Toast.makeText(getApplicationContext(), "해당 가수가 선택됨==> " + item.getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}