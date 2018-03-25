package com.example.jswn.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JSWN on 20-12-2017.
 */

public class MyAdapter extends BaseAdapter {
    List<MyData> arr;
    Context context;

    public MyAdapter(List<MyData> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.rowdesign,parent,false);

        TextView name = convertView.findViewById(R.id.name);
        TextView mobileno = convertView.findViewById(R.id.mobileNo);


        name.setText(arr.get(position).getName());
        mobileno.setText(arr.get(position).getMobileNo());

        return convertView;
    }
}
