package com.example.p2p.utils;

import android.view.View;

import com.example.p2p.common.MyApplication;

/**
 * Created by chenyuelun on 2017/6/20.
 */

public class UiUtils {

    public static View inflate(int id) {
        return View.inflate(MyApplication.getContext(),id,null);
    }
}
