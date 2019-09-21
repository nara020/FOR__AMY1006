package com.example.horim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.horim.adapters.PostAdapter;
import com.example.horim.models.Post;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mPostRecyclerView;
    private PostAdapter mAdapter;
    private List<Post> mDatas;

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mPostRecyclerView = findViewById(R.id.main_recyclerview);




        findViewById(R.id.main_post_edit).setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post)
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(queryDocumentSnapshots !=null){
                            mDatas.clear();

                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()){
                                Map<String, Object> shot = snap.getData();
                                String title = String.valueOf(shot.get(FirebaseID.title));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                                Post data = new Post(documentId, title , contents);
                                mDatas.add(data);

                            }
                            mAdapter= new PostAdapter(mDatas);
                            mPostRecyclerView.setAdapter(mAdapter);

                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
     startActivity(new Intent(this, PostActivity.class));


    }
}
