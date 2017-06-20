package com.example.p2p;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fl_main;
    private RadioGroup rg_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_main = instance(R.id.fl_main);
        rg_main = instance(R.id.rg_main);
    }

    public <T> T instance(int id) {
        return (T) findViewById(id);
    }
}
