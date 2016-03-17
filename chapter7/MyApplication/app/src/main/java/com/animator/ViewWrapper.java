package com.animator;

import android.view.View;

/**
 * Created by weiguangmeng on 16/3/17.
 */
public class ViewWrapper {

    private View targeView;

    public ViewWrapper(View view) {
        targeView = view;
    }

    public int getWidth() {
        return targeView.getWidth();
    }

    public void setWidth(int width) {
        targeView.getLayoutParams().width = width;
        targeView.requestLayout();
    }
}
