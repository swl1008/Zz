package com.example.examination.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.examination.R;
import com.example.examination.adapter.NewsAdapter;
import com.example.examination.bean.NewsBean;
import com.example.examination.presenter.PresenterImpl;
import com.example.examination.view.Iview;

public class FirstFragment extends Fragment implements Iview{
    private PresenterImpl presenter;
    private ImageView image_change;
    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private boolean p;
    public String urlStr ="http://www.xieast.com/api/news/news.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,container,false);
        presenter = new PresenterImpl(this);
        image_change = view.findViewById(R.id.image_change);
        recyclerView = view.findViewById(R.id.recycle);
        //创建布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //创建适配
        adapter = new NewsAdapter(getActivity());
        //设置适配器
        recyclerView.setAdapter(adapter);
        //设置分割线
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        initData();
        image_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p==true){
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    p=false;
                }else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    p=true;
                }
            }
        });
        return view;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
