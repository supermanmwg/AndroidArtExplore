package com.animator;

import android.view.View;

/**
 * Created by weiguangmeng on 16/3/17.
 */
public class HeightWrapper {
    public View targetView;

    public HeightWrapper(View view) {
        targetView = view;
    }

    public int getHeight() {
        return targetView.getHeight();
    }

    public void setHeight(int height) {
        targetView.getLayoutParams().height = height;
        targetView.requestLayout();
    }

}
