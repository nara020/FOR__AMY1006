package com.example.horim.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.horim.R;
import com.example.horim.menudetail;
import com.example.horim.models.MenuInfo;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    Context mContext;
    View v;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView price;
        TextView name;
        TextView explain;
        TextView allegi;
        TextView halal;
        TextView spicy;



        MyViewHolder(View view){
            super(view);
            ivPicture = view.findViewById(R.id.iv_picture);
            price = view.findViewById(R.id.price);
            name = view.findViewById(R.id.name);
            explain = view.findViewById(R.id.explain);
            allegi = view.findViewById(R.id.allegi);
            halal = view.findViewById(R.id.halal);
            spicy = view.findViewById(R.id.spicy);

        }
    }

    private ArrayList<MenuInfo> MenuInfoArrayList;
    public MenuAdapter(Context context, ArrayList<MenuInfo> MenuInfoArrayList){
        this.MenuInfoArrayList = MenuInfoArrayList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chickendetail_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ivPicture.setImageResource(MenuInfoArrayList.get(position).drawableId);
        myViewHolder.price.setText(MenuInfoArrayList.get(position).price);
        myViewHolder.name.setText(MenuInfoArrayList.get(position).name);
        myViewHolder.explain.setText(MenuInfoArrayList.get(position).explain);
        myViewHolder.allegi.setText(MenuInfoArrayList.get(position).allegi);
        myViewHolder.halal.setText(MenuInfoArrayList.get(position).halal);
        myViewHolder.spicy.setText(MenuInfoArrayList.get(position).spicy);



        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();
                bundle2.putCharSequence("asd","123123");
                bundle2.putCharSequence("date",MenuInfoArrayList.get(position).price);

                Intent intent = new Intent(mContext, menudetail.class);//더 상세 정보 페이지\
                intent.putExtra("adapter",bundle2);
                ((Activity) mContext).startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MenuInfoArrayList.size();
    }




}
