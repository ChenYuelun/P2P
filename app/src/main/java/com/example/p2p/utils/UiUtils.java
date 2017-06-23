package com.example.p2p.utils;

import android.content.Context;
import android.view.View;

import com.example.p2p.common.MyApplication;


/**
 * Created by chenyuelun on 2017/6/20.
 */

public class UiUtils {


    public static View inflate(int id) {
        return View.inflate(getContext(),id,null);
    }


    public static String getVersionString(int id, String value) {
        return String.format(getStringForResources(id),value);
    }

    private static String getStringForResources(int id) {
        return getContext().getResources().getString(id);
    }

    public static Context getContext(){
        return MyApplication.getContext();
    }




}
