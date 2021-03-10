package com.deepspc.workshop.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.deepspc.workshop.R;
import com.deepspc.workshop.adapter.FaultListAdapter;
import com.deepspc.workshop.model.FaultItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomsFragment extends Fragment {

    public static RoomsFragment newInstance(int index,
                                            ArrayList<String> titles,
                                            ArrayList<String> rooms,
                                            ArrayList<String> devices,
                                            ArrayList<String> sides,
                                            ArrayList<String> faults) {
        RoomsFragment fragment = new RoomsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("section_number", index);
        bundle.putString("section_title", titles.get(index));
        bundle.putStringArrayList("rooms", rooms);
        bundle.putStringArrayList("devices", devices);
        bundle.putStringArrayList("sides", sides);
        bundle.putStringArrayList("faults", faults);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        String title = getArguments().getString("section_title");
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);
        if ("二科".equals(title)) {
            RelativeLayout tiZhu = view.findViewById(R.id.layout_ti_zhu);
            tiZhu.setVisibility(View.GONE);
            LinearLayout condition = view.findViewById(R.id.fault_confirm);
            condition.setVisibility(View.GONE);
        } else if ("故障确认".equals(title)) {
            LinearLayout roomsFault = view.findViewById(R.id.rooms_fault);
            roomsFault.setVisibility(View.GONE);
            //加载下拉框数据
            initSpinner(view);
            //初始化列表
            initListView(view);
        } else {
            LinearLayout condition = view.findViewById(R.id.fault_confirm);
            condition.setVisibility(View.GONE);
        }
        return view;
    }

    private void initSpinner(View view) {
        ArrayList<String> roomsData = getArguments().getStringArrayList("rooms");
        SpinnerAdapter roomsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, roomsData);
        Spinner rooms = view.findViewById(R.id.spinner_rooms);
        rooms.setAdapter(roomsAdapter);

        ArrayList<String> devicesData = getArguments().getStringArrayList("devices");
        SpinnerAdapter devidesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, devicesData);
        Spinner device = view.findViewById(R.id.spinner_device_num);
        device.setAdapter(devidesAdapter);

        ArrayList<String> sidesData = getArguments().getStringArrayList("sides");
        SpinnerAdapter sidesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sidesData);
        Spinner spindle = view.findViewById(R.id.spinner_spindle_side);
        spindle.setAdapter(sidesAdapter);

        ArrayList<String> faultsData = getArguments().getStringArrayList("faults");
        SpinnerAdapter faultsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, faultsData);
        Spinner faultType = view.findViewById(R.id.spinner_fault_type);
        faultType.setAdapter(faultsAdapter);
    }

    private void initListView(View view) {
        ListView listView = view.findViewById(R.id.fault_list);
        List<FaultItem> listItems = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            FaultItem item = new FaultItem(i + "", r.nextInt(40) +"#故障", "11:21");
            listItems.add(item);
        }
        FaultListAdapter faultListAdapter = new FaultListAdapter(getActivity(), listItems);
        Button selectAll = view.findViewById(R.id.select_all);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faultListAdapter.allCheck();
            }
        });
        listView.setAdapter(faultListAdapter);
    }

}
