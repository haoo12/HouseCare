package com.letmecare.housecare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.letmecare.housecare.R;
import com.letmecare.housecare.models.Day;

import java.util.ArrayList;
import java.util.List;

public class ListVIewAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Day> list;

    public ListVIewAdapter(Context context, int layout, List<Day> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(layout, null);

        TextView textView = view.findViewById(R.id.tv_day);
        TextView textView1 = view.findViewById(R.id.tv_booktime);
        ImageView imageView = view.findViewById(R.id.btnadd);

        String day = list.get(i).getDay();
        String booktime = list.get(i).getBookTime();
        int btn = list.get(i).getBtnAdd();

        textView.setText(day);
        textView1.setText(booktime);
        imageView.setImageResource(btn);

        return view;
    }
    public void updateData(List<Day> List, int position, String str) {
//        list.clear();
        List.get(position).setBookTime(str);
//        list.addAll(newList);
        notifyDataSetChanged();
    }
    public List<String> getBookTimeAll() {
        List<String> listbooktime = new ArrayList<>();

        for(int i = 0; i < list.size(); i ++) {
            listbooktime.add(list.get(i).getBookTime());
        }
        return listbooktime;
    }
    public String getBookTimeString() {
        StringBuilder listbooktime = new StringBuilder();

        for(int i = 0; i < list.size(); i ++) {
            listbooktime.append(list.get(i).getDay()).append(": ").append(list.get(i).getBookTime()).append("\n");
        }
        return listbooktime.toString();
    }

    public void updateDataDay(List<Day> List, String str) {
        List.clear();
        List.add(new Day("Thời gian chi tiết", R.drawable.baseline_add_circle_24, ""));
//        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void updateDataWeek(List<Day> List, String str) {
        List.clear();
        list.add(new Day("Thứ 2", R.drawable.baseline_add_circle_24, ""));
        list.add(new Day("Thứ 3", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Thứ 4", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Thứ 5", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Thứ 6", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Thứ 7", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Chủ nhật", R.drawable.baseline_add_circle_24,""));

//        list.addAll(newList);
        notifyDataSetChanged();
    }


}
