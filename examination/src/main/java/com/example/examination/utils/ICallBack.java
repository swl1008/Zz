package com.example.examination.utils;

import java.io.IOException;

public interface ICallBack {
    void success(Object o);
    void failed(IOException e);
}
