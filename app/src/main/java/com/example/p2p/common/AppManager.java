package com.example.p2p.common;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.util.Stack;

/**
 * Created by chenyuelun on 2017/6/21.
 */

public class AppManager {

    private AppManager (){};
    private static AppManager appManager = new AppManager();

    public static AppManager getInstance(){
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();


    //添加Activity
    public void addActivity(Activity activity){
        Log.e("TAG","addActivity"+activity.getClass().getSimpleName());
        if(activity != null) {
            stack.add(activity);
        }
    }


    //删除指定的Activity
    public void removeActivity(Activity activity){
        Log.e("TAG","removeActivity"+activity.getClass().getSimpleName());
        if(activity != null) {
            for(int i = stack.size()-1; i >= 0; i--) {
                Activity currentActivity = stack.get(i);
                if(activity == currentActivity) {
                    activity.finish();
                    stack.remove(i);
                }
            }
        }

//        if(stack.contains(activity)) {
//            activity.finish();
//            stack.remove(activity);
//        }
    }

    //删除所有的Activity
    public void removeAllActivity(){

        for(int i = stack.size()-1; i >= 0; i--) {
            Activity currentActivity = stack.get(i);
            if(currentActivity == null) {
                currentActivity.finish();
                stack.remove(currentActivity);
            }
        }

//        for (int i = stack.size()-1; i >=0 ; i++) {
//
//            Activity currentActivity = stack.get(i);
//
//            if (currentActivity != null){
//                currentActivity.finish();
//                stack.remove(i);
//            }
//        }


//        Log.e("TAG","removeAllActivity");
//        for(Activity activity:stack) {
//            if(activity != null) {
//                activity.finish();
//            }
//        }
//        stack.clear();

    }


}
