package com.example.p2p.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.p2p.R;
import com.example.p2p.common.MyApplication;
import com.example.p2p.utils.DensityUtils;
import com.example.p2p.utils.UiUtils;

import static android.R.attr.radius;
import static android.R.attr.strokeWidth;

/**
 * Created by chenyuelun on 2017/6/23.
 */

public class ProgressView extends View {
    private int textSize;
    private int textColor;
    private int arcColor;
    private int circleColor;
    private Paint paint;
    private int viewWidth;
    private int viewHeight;
    private int strokeWidth = DensityUtils.dp2px(MyApplication.getContext(), 10);
    //    private int strokeWidth = 10;
    private float sweeoAngle = 0;

    public ProgressView(Context context, int circleColor) {
        super(context);
        init();
    }


    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        if (typedArray != null && typedArray.length() > 0) {
            //圆的颜色
            int circleColor = typedArray.getColor(R.styleable.ProgressView_circleColor, Color.GREEN);
            this.circleColor = circleColor;
            //圆弧的颜色
            int arcColor = typedArray.getColor(R.styleable.ProgressView_arcColor, Color.RED);
            this.arcColor = arcColor;
            //文本颜色
            int textColor = typedArray.getColor(R.styleable.ProgressView_textColor, Color.BLACK);
            this.textColor = textColor;
            //背景
            int textSize = typedArray.getInt(R.styleable.ProgressView_textSize, 15);
            this.textSize = textSize;


        }
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getWidth();
        viewHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆
        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.STROKE);//设置描边
        paint.setStrokeWidth(strokeWidth);
        int cx = viewWidth / 2;//圆心X坐标
        int cy = viewHeight / 2;//圆心Y坐标
        int radius = viewWidth / 2 - strokeWidth / 2;//半径
        canvas.drawCircle(cx, cy, radius, paint);

        //画弧
        paint.setColor(arcColor);
        RectF rectF = new RectF();
        rectF.set(strokeWidth / 2, strokeWidth / 2, viewWidth - strokeWidth / 2, viewHeight - strokeWidth / 2);
        canvas.drawArc(rectF, 0, sweeoAngle / 100 * 360, false, paint);

        //画文本
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        String str = sweeoAngle + "%";
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        paint.setTextSize(DensityUtils.sp2px(MyApplication.getContext(), textSize));
        int textWidth = rect.width();
        int textHeght = rect.height();
        int tx = viewWidth / 2 - textWidth / 2;
        int ty = viewHeight / 2 + textHeght / 2;
        canvas.drawText(str, tx, ty, paint);

    }


    public void setSweeoAngle(int sweeoAngle) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, sweeoAngle);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ProgressView.this.sweeoAngle = value;
                invalidate();
            }
        });
        valueAnimator.start();
    }

}
