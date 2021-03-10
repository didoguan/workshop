package com.deepspc.workshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.deepspc.workshop.ui.main.RoomTabPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.main_view_page);
        TabLayout tabs = findViewById(R.id.main_tabs);
        viewPager.setAdapter(new RoomTabPageAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);
    }
}