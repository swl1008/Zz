package com.example.examination.model;

import com.example.examination.bean.NewsBean;
import com.example.examination.utils.ICallBack;
import com.example.examination.utils.OkHttpUtil;

import java.io.IOException;

public class ModelImpl implements Imodel{

    @Override
    public void requestData(String url, final MyCallBack myCallBack) {
        OkHttpUtil.getInstance().yibuGet(url, NewsBean.class, new ICallBack() {
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
