package com.drawables;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View levelList;
    View transitionView;
    View scaleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        levelList = findViewById(R.id.level_list);
        Drawable drawable = levelList.getBackground();
        drawable.setLevel(1);

        levelList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = levelList.getBackground();
                drawable.setLevel(2);
            }
        });

        transitionView = findViewById(R.id.transition);
        final TransitionDrawable drawable1 = (TransitionDrawable) transitionView.getBackground();
        drawable1.startTransition(1000);
        transitionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable1.reverseTransition(1000);
            }
        });

        scaleView = findViewById(R.id.scale);
        ScaleDrawable scaleDrawable = (ScaleDrawable) scaleView.getBackground();
        scaleDrawable.setLevel(1);

        View customDrawable = findViewById(R.id.custom_drawable);
        customDrawable.setBackground(new CustomDrawable(Color.parseColor("#002345")));
    }
}
