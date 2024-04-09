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
import com.letmecare.housecare.models.Information;

import java.util.List;

public class ListViewInformationAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Information> list;

    public ListViewInformationAdapter(Context context, int layout, List<Information> list) {
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

        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(layout, null);

        TextView textView = view.findViewById(R.id.tv_title);
        ImageView imageView = view.findViewById(R.id.icon_book);

        String title = list.get(i).getTitle();
        int btn = list.get(i).getSrc();

        textView.setText(title);
        imageView.setImageResource(btn);

        return view;
    }
    public void updateData(List<Information> List, int position, String str) {
//        list.clear();
        list.get(position).setTitle(str);
//        list.addAll(newList);
        notifyDataSetChanged();
    }
    public String getLocation(List<Information> List, int position) {

        return list.get(position).getTitle();

    }
}
