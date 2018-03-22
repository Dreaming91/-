package com.wsyd.myanimationviewgit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wsyd on 2018/3/19.
 */

public class AnimationView extends View {

    private List<Float> mPercentList = new ArrayList<>();
    private float tempPercent;
    public static final int SMALL = 7;
    public static final int NORMAL = 5;
    public static final int BIG = 3;
    private int size = NORMAL;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                invalidate();
                mHandler.sendEmptyMessageDelayed(10, 1000);
            }
        }
    };
    private float mBaseLong;


    public AnimationView(Context context) {
        this(context, null);
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mBaseLong = getMeasuredWidth() / size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        for (int i = 0; i < mPercentList.size(); i++) {

//            float percentLong1 = baseLong * mPercentList.get(i);
//            float percentLong2 = (baseLong * 4) * mPercentList.get(i);
//            float long1 = baseLong + percentLong1;
//            float long2 = (baseLong * 4) - percentLong2;

            RectF rectF = new RectF(mBaseLong, mBaseLong, mBaseLong * 4, mBaseLong * 4);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(RodomColor());
            canvas.drawArc(rectF, tempPercent, 360 * mPercentList.get(i), true, paint);
            tempPercent += (360 * mPercentList.get(i));

            float centerX = rectF.centerX();
            float centerY = rectF.centerY();
            Paint paintCircle = new Paint();
            paintCircle.setColor(Color.rgb(255, 255, 255));
            canvas.drawCircle(centerX, centerY, mBaseLong / 3, paintCircle);
        }


    }

    //炫彩
    private int RodomColor() {
        Random random = new Random();
        int nextInt1 = random.nextInt(255);
        int nextInt2 = random.nextInt(255);
        int nextInt3 = random.nextInt(255);
        return Color.rgb(nextInt1, nextInt2, nextInt3);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setList(List<Integer> list) {
        int all = 0;
        for (int i = 0; i < list.size(); i++) {
            int integer = list.get(i);
            all += integer;
        }
        for (int i = 0; i < list.size(); i++) {
            int integer = list.get(i);
            float percent = (float) integer / (float) all;
            mPercentList.add(percent);
        }
    }

    public void start() {
        mHandler.sendEmptyMessage(10);
        invalidate();
    }

}
