package com.example.moni333.model;

import com.example.moni333.bean.NewsBean;
import com.example.moni333.utils.ICallBack;
import com.example.moni333.utils.OkHttpUtil;

import java.io.IOException;

public class ModelImpl implements Imodel {
    @Override
    public void requestData(String url, final MyCallBack myCallBack) {
        OkHttpUtil.getInstance().doGet(url, NewsBean.class, new ICallBack() {
            @Override
            public void success(Object o) {
                myCallBack.getData(o);
            }

            @Override
            public void failed(IOException e) {

            }
        });
    }
}
