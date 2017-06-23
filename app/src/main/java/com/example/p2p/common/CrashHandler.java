package com.example.p2p.common;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by chenyuelun on 2017/6/21.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    private CrashHandler() {
    }

    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance() {
        return crashHandler;
    }

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.d("crash", "uncaughtException" + e.getMessage());
//        ThreadManager.getInstance().getThread().execute(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(context, "系统崩溃", Toast.LENGTH_SHORT).show();
//                Looper.loop();
//            }
//        });
        //1.提醒用户
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(MyApplication.getContext(), "软件崩溃了", Toast.LENGTH_SHORT).show();
                Looper.loop();

            }
        }.start();

        //2、收集异常信息
        collectionInfo(e);

        //3、退出应用
        Log.e("TAG", "移除所有Activity");
        AppManager.getInstance().removeAllActivity();

        SystemClock.sleep(500);
        //杀死当前的进程
        Log.e("TAG", "杀死当前的进程");
        Process.killProcess(Process.myPid());
        //退出虚拟机  参数0，其他任何参数都表示非正常退出
        Log.e("TAG", "退出虚拟机");
        System.exit(0);


    }

    private void collectionInfo(Throwable e) {
        //收集收集信息及异常信息保存到本地，待下次进入软件上传至服务器
    }
}
