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

import com.example.horim.chickendetail;
import com.example.horim.models.FoodInfo;
import com.example.horim.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    Context mContext;
    View v;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvPrice;



        MyViewHolder(View view){
            super(view);
            ivPicture = view.findViewById(R.id.iv_picture);
            tvPrice = view.findViewById(R.id.tv_price);


        }
    }

    private ArrayList<FoodInfo> foodInfoArrayList;
    public MyAdapter(Context context, ArrayList<FoodInfo> foodInfoArrayList){
        this.foodInfoArrayList = foodInfoArrayList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chicken_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ivPicture.setImageResource(foodInfoArrayList.get(position).drawableId);
        myViewHolder.tvPrice.setText(foodInfoArrayList.get(position).store);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();
                bundle2.putCharSequence("asd","123123");
                bundle2.putCharSequence("date",foodInfoArrayList.get(position).store);

                Intent intent = new Intent(mContext, chickendetail.class);
                intent.putExtra("adapter",bundle2);
                ((Activity) mContext).startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodInfoArrayList.size();
    }




}
