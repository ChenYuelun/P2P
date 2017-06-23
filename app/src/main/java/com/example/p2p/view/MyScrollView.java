package com.example.p2p.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by chenyuelun on 2017/6/23.
 */

public class MyScrollView extends ScrollView {

    private View childView;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int downY;
    private Rect rect;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = eventY;

                break;
            case MotionEvent.ACTION_MOVE:
                int dY = eventY - downY;
                if(rect == null) {
                    rect = new Rect();
                    rect.set(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());
                }
                childView.layout(
                        childView.getLeft(),
                        childView.getTop()+dY,
                        childView.getRight(),
                        childView.getBottom()+dY
                );
                downY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                if(rect != null) {
                    int translateY = childView.getTop() - rect.top;
                    TranslateAnimation ta = new TranslateAnimation(0,0,0,-translateY);
                    ta.setDuration(300);
                    ta.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isOnAnimStart = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            childView.setAnimation(null);
                            childView.layout(rect.left,rect.top,rect.right,rect.bottom);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.startAnimation(ta);

                }
                break;
        }
        return super.onTouchEvent(ev);
    }
    private boolean isOnAnimStart = false;
    private int downX;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int evenX = (int) ev.getX();
        int evenY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN :
                isOnAnimStart = false;
                downX = evenX;
                downY = evenY;
                break;
            case MotionEvent.ACTION_MOVE:
                int dX = Math.abs(evenX - downX);
                int dY = Math.abs(evenY - downY);
                if(dY > dX && dY > 20) {
                    isOnAnimStart = true;
                }
                downX =evenX;
                downX = evenY;
                break;
        }

        return isOnAnimStart;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }
}
