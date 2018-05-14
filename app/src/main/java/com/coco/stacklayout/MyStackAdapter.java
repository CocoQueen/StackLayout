package com.coco.stacklayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fashare.stack_layout.StackLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ydx on 18-5-14.
 */

public class MyStackAdapter extends StackLayout.Adapter<MyStackAdapter.ViewHolder> {
    List<String> list;
    Context context;
    static List<Integer> sRandomColors = new ArrayList<>();
    static{
        for(int i=0; i<100; i++)
            sRandomColors.add(new Random().nextInt() | 0xff000000);
    }
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public MyStackAdapter(List<String> list,Context context) {
        this.list = list;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTv.setText(list.get(position));
        holder.itemView.findViewById(R.id.layout_content).setBackgroundColor(sRandomColors.get(position%sRandomColors.size()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends StackLayout.ViewHolder{

        TextView mTv;
        public ViewHolder(View itemView) {
            super(itemView);
            mTv=itemView.findViewById(R.id.mTv);
        }
    }
}
