package com.example.horim.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.horim.R;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReviewFragment extends Fragment {

    boolean moreSwitcher;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //프래그먼트 메뉴를 인플레이트해주고 컨테이너에 붙여달라는 뜻임
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.review, container, false);
        final Button expand = (Button)rootView.findViewById(R.id.expand);
        final TextView infoView = (TextView)rootView.findViewById(R.id.infoview);
        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!moreSwitcher) {
                    infoView.setMaxLines(infoView.getLineCount());
                    infoView.setLines(infoView.getLineCount());
                    moreSwitcher = true;
                    expand.setText("축소");
                }else{
                    infoView.setMaxLines(5);
                    infoView.setLines(5);
                    moreSwitcher = false;
                    expand.setText("확장");
                }
            }
        });


        return rootView;
    }

    //----------------------------------------------------------------------------- 사장님 공지 접었따 폈다시키기



//-------------------------------------------------------------------------------

}
