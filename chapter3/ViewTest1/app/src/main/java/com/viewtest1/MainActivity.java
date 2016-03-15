package com.viewtest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button scroll;
    View  moveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scroll = (Button)findViewById(R.id.scroll);
        moveView = findViewById(R.id.move_view);
        scroll.setOnClickListener(this);
        moveView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.scroll:
                MarginLayoutParams params = (MarginLayoutParams) moveView.getLayoutParams();
                // params.height += 100;
                params.topMargin += 100;
                moveView.setLayoutParams(params);
                break;
            case R.id.move_view:
                Toast.makeText(MainActivity.this, "move view is on clicked", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
