package com.wsyd.myanimationviewgit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Integer> mIntegerList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnimationView view = findViewById(R.id.view);
        mIntegerList.add(60);
        mIntegerList.add(60);
        view.setList(mIntegerList);
        view.setSize(AnimationView.SMALL);
        view.start();

    }

}
