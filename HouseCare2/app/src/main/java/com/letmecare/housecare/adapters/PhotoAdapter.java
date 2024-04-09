package com.letmecare.housecare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.letmecare.housecare.R;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    Context context;
    List<Integer> listPhoto;
    public PhotoAdapter(Context context, List<Integer> listPhoto) {
        this.context = context;
        this.listPhoto = listPhoto;
    }

    @Override
    public int getCount() {
        return listPhoto.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);

        ImageView img = view.findViewById(R.id.image);

        int photo = listPhoto.get(position);
        Glide.with(context).load(photo).into(img);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);

//        super.destroyItem(container, position, object);
    }
}
