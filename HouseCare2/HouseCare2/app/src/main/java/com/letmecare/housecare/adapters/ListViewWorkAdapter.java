package com.letmecare.housecare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.letmecare.housecare.R;
import com.letmecare.housecare.models.Day;
import com.letmecare.housecare.models.Work;

import java.util.List;

public class ListViewWorkAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Work> list;

    public ListViewWorkAdapter(Context context, int layout, List<Work> list) {
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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(layout, null);
        // Ánh xạ các thành phần trong item_work
        TextView dayStart = view.findViewById(R.id.tvStart);
        TextView dayEnd = view.findViewById(R.id.tvEnd);
        TextView Type = view.findViewById(R.id.tvType);
        TextView Address = view.findViewById(R.id.tvAddress);
        TextView Option = view.findViewById(R.id.tvOption);
        TextView Total = view.findViewById(R.id.tv_total);

        Work work = list.get(i);

        dayStart.setText(work.getDayStart());
        dayEnd.setText(work.getDayEnd());
        Type.setText(work.getType());
        Address.setText(work.getAddress());
        Option.setText(work.getOption());
        Total.setText(work.getTotal());

        return view;
    }

    public void updateData(List<Work> List) {
        list = List;
        notifyDataSetChanged();
    }
}
