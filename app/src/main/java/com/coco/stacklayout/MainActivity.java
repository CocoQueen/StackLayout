package com.coco.stacklayout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fashare.stack_layout.StackLayout;
import com.fashare.stack_layout.transformer.AlphaTransformer;
import com.fashare.stack_layout.transformer.AngleTransformer;
import com.fashare.stack_layout.transformer.StackPageTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StackLayout stackLayout;
    List<String>list=new ArrayList<>();
    private MyStackAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData(0);
        stackLayout = findViewById(R.id.stack_layout);
        adapter = new MyStackAdapter(list,this);
        stackLayout.setAdapter(adapter);
        stackLayout.addPageTransformer(new StackPageTransformer(),new AngleTransformer(),new MyAlphaTransformer());
        stackLayout.setOnSwipeListener(new StackLayout.OnSwipeListener() {
            @Override
            public void onSwiped(View swipedView, int swipedItemPos, boolean isSwipeLeft, int itemLeft) {
              if (isSwipeLeft){
                  Toast.makeText(MainActivity.this, "喜欢", Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(MainActivity.this, "不喜欢", Toast.LENGTH_SHORT).show();
              }
                int curPage=0;
                if (itemLeft<5) {
                    loadData(++curPage);
                }
            }
        });
    }

    private void loadData(final int i) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.getList().addAll(Arrays.asList(String.valueOf(i*3),String.valueOf(i*3+1),String.valueOf(i*3+2)));
                adapter.notifyDataSetChanged();
            }
        },1000);
    }
}
