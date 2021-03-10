package com.deepspc.workshop.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class RoomTabPageAdapter extends FragmentPagerAdapter {
    //选项卡数
    private ArrayList<String> titles;
    //科室
    private ArrayList<String> rooms;
    //设备号
    private ArrayList<String> devices;
    //锭位
    private ArrayList<String> sides;
    //故障类别
    private ArrayList<String> faults;

    public RoomTabPageAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        titles = new ArrayList<>();
        titles.add("一科");
        titles.add("二科");
        titles.add("故障确认");

        rooms = new ArrayList<>();
        rooms.add("科室");
        rooms.add("一科");
        rooms.add("二科");

        devices = new ArrayList<>();
        devices.add("设备号");
        for (int i = 1; i <= 20; i++) {
            devices.add("设备：" + i);
        }

        sides = new ArrayList<>();
        sides.add("锭位");
        sides.add("左面");
        sides.add("右面");

        faults = new ArrayList<>();
        faults.add("故障类别");
        faults.add("待确定");
        faults.add("电气故障");
        faults.add("机械故障");
        faults.add("已就绪");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return RoomsFragment.newInstance(position, titles, rooms, devices, sides, faults);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (null != titles) {
            return titles.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (null != titles) {
            return titles.size();
        }
        return 0;
    }
}
