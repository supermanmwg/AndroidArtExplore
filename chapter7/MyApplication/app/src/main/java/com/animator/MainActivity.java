package com.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends AppCompatActivity implements ObjectAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

    private static final String TAG = "MainActivity";
    ImageView course;
    View changeWidth;
    int courseWidth;
    int courseEndWidth;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        course = (ImageView) findViewById(R.id.course);
        courseWidth = course.getWidth();
        courseEndWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, getResources().getDisplayMetrics());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(course, "alpha", 0.0f, 0.5f, 1.0f);
            objectAnimator.setDuration(10000);
            //objectAnimator.setRepeatMode(ValueAnimator.INFINITE);
            objectAnimator.addListener(this);
            objectAnimator.start();

            ValueAnimator valueAnimator = ValueAnimator.ofInt(100, 200);
            valueAnimator.addUpdateListener(this);
            valueAnimator.setDuration(10000);
            valueAnimator.start();

            changeWidth = findViewById(R.id.change_width);
            changeWidth.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "hahahaha!", Toast.LENGTH_SHORT).show();
                }
            });
            ViewWrapper viewWrapper = new ViewWrapper(changeWidth);
            //  ObjectAnimator.ofInt(viewWrapper, "width", 0, 500, 1000).setDuration(10000).start();
            HeightWrapper heightWrapper = new HeightWrapper(changeWidth);
            // ObjectAnimator.ofInt(heightWrapper, "height", 0, 500, 1000).setDuration(10000).start();

            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofInt(viewWrapper, "width", 0, 500, 1000).setDuration(10000),
                    ObjectAnimator.ofInt(heightWrapper, "height", 0, 500, 1000).setDuration(10000)
            );

            set.start();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int currentValue = (int) animation.getAnimatedValue();
        Log.d(TAG, "current value: " + currentValue);

        float fraction = animation.getAnimatedFraction();
        Log.d(TAG, "fraction is " + fraction);
        course.getLayoutParams().width = (int) (courseWidth + (courseEndWidth - courseWidth) * fraction);
        course.requestLayout();
    }

    @Override
    public void onAnimationStart(Animator animation) {
        Toast.makeText(this, "animator is start", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Toast.makeText(this, "animator is end", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        Toast.makeText(this, "animator is canceled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        Toast.makeText(this, "animator is repeating", Toast.LENGTH_SHORT).show();
    }
}
