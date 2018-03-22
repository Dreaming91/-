package com.wsyd.myanimationview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wsyd on 2018/3/19.
 */

public class AnimationView extends View {

    private RectF rectF;
    private List<Float> mPercentList = new ArrayList<>();
    private float tempPercent;
    private Message mMessage = new Message();
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float baseLong = getWidth() / 5;
        for (int i = 0; i < mPercentList.size(); i++) {
            float percentLong1 = baseLong * mPercentList.get(i);
            float percentLong2 = (baseLong * 4) * mPercentList.get(i);
            float long1 = baseLong + percentLong1;
            float long2 = (baseLong * 4) - percentLong2;

            RectF rectF = new RectF(baseLong, baseLong, baseLong * 4, baseLong * 4);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(RodomColor());
            canvas.drawArc(rectF, tempPercent, 360 * mPercentList.get(i), true, paint);
            tempPercent += (360 * mPercentList.get(i));
            float centerX = rectF.centerX();
            float centerY = rectF.centerY();
            Paint paintCircle = new Paint();
            paintCircle.setColor(Color.rgb(255,255,255));
            canvas.drawCircle(centerX, centerY, baseLong / 3, paintCircle);
            Log.i("testontest", "percent-->" + mPercentList.get(i) + " / baselong-->" + baseLong + " \n long1-->" + long1 + " / long2-->" + long2 + " / percentlong1-->" + percentLong1 + " / percentlong2-->" + percentLong2);
        }


    }

    private int RodomColor() {
        Random random = new Random();
        int nextInt1 = random.nextInt(255);
        int nextInt2 = random.nextInt(255);
        int nextInt3 = random.nextInt(255);
        return Color.rgb(nextInt1, nextInt2, nextInt3);
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
        mHandler.sendEmptyMessage(10);
        invalidate();
    }

}
