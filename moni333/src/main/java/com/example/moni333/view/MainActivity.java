package com.example.moni333.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moni333.R;
import com.example.moni333.adapter.NewsAdapter;
import com.example.moni333.bean.NewsBean;
import com.example.moni333.presenter.PresenterImpl;

public class MainActivity extends AppCompatActivity implements Iview{

    private PresenterImpl presenter;
    private ImageView image_heart;
    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private boolean check = true;
    public String urlStr ="http://www.xieast.com/api/news/news.php?page=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PresenterImpl(this);
        initView();
        Donghua();
        initData();
    }

    private void initView() {

        recyclerView = findViewById(R.id.recycle);
        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //创建适配
        adapter = new NewsAdapter(MainActivity.this);
        //设置适配器
        recyclerView.setAdapter(adapter);
        //设置分割线
        DividerItemDecoration decoration = new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setItemOnClickListener(new NewsAdapter.onClickCallBack() {
            @Override
            public void setItemOnClick(final int i) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除");
                builder.setMessage("确认删除么？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.delData(i);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"你点击了取消",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

            }
        });


    }
    public void Donghua(){
        image_heart = findViewById(R.id.image_heart);
        image_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ObjectAnimator translation_X = ObjectAnimator.ofFloat(image_heart, "translationX", 0,-1100,0);
                ObjectAnimator translation_Y = ObjectAnimator.ofFloat(image_heart, "translationY", 0,1900,0);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(image_heart, "alpha", 1f, 0f,1f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(3000);
                animatorSet.playTogether(translation_X, translation_Y, alpha);


                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (check){
                            image_heart.setImageResource(R.drawable.black);
                            check=false;
                        }else {
                            image_heart.setImageResource(R.drawable.white);
                            check=true;
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        super.onAnimationRepeat(animation);
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                });
                animatorSet.start();
            }
        });
    }

    @Override
    public void getRequest(Object o) {
        if (o instanceof NewsBean) {
            NewsBean bean = (NewsBean) o;
            adapter.setData(bean.getData());
        }
    }
    private void initData() {
        presenter.startRequest(urlStr,null,NewsBean.class);
    }
}
