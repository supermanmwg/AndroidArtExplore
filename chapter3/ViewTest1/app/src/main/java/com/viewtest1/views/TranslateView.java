package com.viewtest1.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by weiguangmeng on 16/3/5.
 */
public class TranslateView extends View {

    private final static String TAG = "TranslateView";
    private int mLastX;
    private int mLastY;

    public TranslateView(Context context) {
        super(context, null);
    }

    public TranslateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLastY = getTop();
        mLastX = getLeft();
        Log.d(TAG, "init x is " + mLastX + ", y is " + mLastY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        Log.d(TAG, "x is " + x + ", y is " + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "move, deltaX:" + deltaX + ", deltaY:" + deltaY);
                int translationX = (int) (ViewHelper.getTranslationX(this) + deltaX);
                int translationY = (int) (ViewHelper.getTranslationY(this) + deltaX);
                ViewHelper.setTranslationX(this, translationX);
                ViewHelper.setTranslationY(this, translationY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;

        return true;
    }
}
