package com.deepspc.workshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.deepspc.workshop.R;
import com.deepspc.workshop.model.FaultItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaultListAdapter extends BaseAdapter {

    private Context context;
    private List<FaultItem> itemList;
    private LayoutInflater layoutInflater;
    private Map<Integer, FaultItem> datas = new HashMap<>();

    public FaultListAdapter(Context context, List<FaultItem> itemList) {
        this.context = context;
        this.itemList = itemList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.fault_item, null);
        }
        FaultItem faultItem = itemList.get(position);
        faultItem.setId(position + "");
        TextView itemName = convertView.findViewById(R.id.fault_item_name);
        itemName.setText(faultItem.getName());
        TextView itemDate = convertView.findViewById(R.id.fault_item_date);
        itemDate.setText(faultItem.getDate());
        CheckBox checkBox = convertView.findViewById(R.id.fault_check);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    faultItem.setChecked(true);
                    datas.put(position, faultItem);
                } else {
                    faultItem.setChecked(false);
                    datas.remove(position);
                }
            }
        });
        if (null != datas && datas.containsKey(position)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        return convertView;
    }

    public List<FaultItem> getCheckedData() {
        List<FaultItem> checkeds = new ArrayList<>();
        for (Map.Entry<Integer, FaultItem> entry : datas.entrySet()) {
            checkeds.add(entry.getValue());
        }
        return checkeds;
    }

    public void allCheck() {
        if (null == datas || datas.isEmpty()) {
            for (FaultItem item : itemList) {
                datas.put(Integer.parseInt(item.getId()), item);
            }
        } else {
            datas.clear();
        }
    }
}
