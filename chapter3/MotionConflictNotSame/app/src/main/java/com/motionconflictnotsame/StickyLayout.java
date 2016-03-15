package com.motionconflictnotsame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by weiguangmeng on 16/3/9.
 */
public class StickyLayout extends LinearLayout {
    public static final int STATUS_EXPAND = 1;
    public static final int STATUS_COLLAPSED = 2;
    private int headerHeight;
    private int mOriginalHeaderHeight;
    private int mStatus;
    private int mTouchSlop;
    private int mLastX = 0;
    private int mLastY = 0;
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;
    private boolean mIsSticky = true;
    private boolean mDisallowInterceptTouchEventOnHeader = true;
    private IGiveUpTouchEventListener mGiveUpTouchEventListener;

    public StickyLayout(Context context) {
        super(context);
    }

    public StickyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        int intercepted = 0;
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (mDisallowInterceptTouchEventOnHeader && y <= getHeaderHeight()) {
                    intercepted = 0;
                } else if (Math.abs(deltaY) <= Math.abs(deltaY)) {
                    intercepted = 0;
                } else if (mStatus == STATUS_EXPAND && deltaY <= -mTouchSlop) {
                    intercepted = 1;
                } else if(mGiveUpTouchEventListener != null) {
                    if(mGiveUpTouchEventListener.giveUpTouchEvent(event) && deltaY > mTouchSlop) {
                        intercepted = 1;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        mLastXIntercept = x;
        mLastYIntercept = y;
        mLastX = x;
        mLastY = y;

        return intercepted != 0 && mIsSticky;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!mIsSticky) {
            return true;        //测试一下
        }

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                headerHeight += deltaY;
                setHeaderHeight(headerHeight);
                break;
            case MotionEvent.ACTION_UP:
                int destHeight = 0;
                if(headerHeight <= mOriginalHeaderHeight * 0.5) {
                    destHeight = 0;
                    mStatus = STATUS_COLLAPSED;
                } else {
                    destHeight = mOriginalHeaderHeight;
                    mStatus = STATUS_EXPAND;
                }

                //慢慢滑向终点
                //this.smoothsSetHeaderHeight(mHeaderHeight, destHeight, 500);
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;

        return true;
    }

    public int getHeaderHeight() {
        return headerHeight;
    }

    public boolean ismDisallowInterceptTouchEventOnHeader() {
        return mDisallowInterceptTouchEventOnHeader;
    }

    public void setmDisallowInterceptTouchEventOnHeader(boolean mDisallowInterceptTouchEventOnHeader) {
        this.mDisallowInterceptTouchEventOnHeader = mDisallowInterceptTouchEventOnHeader;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;

    }

    public void setmGiveUpTouchEventListener(IGiveUpTouchEventListener mGiveUpTouchEventListener) {
        this.mGiveUpTouchEventListener = mGiveUpTouchEventListener;
    }
}
