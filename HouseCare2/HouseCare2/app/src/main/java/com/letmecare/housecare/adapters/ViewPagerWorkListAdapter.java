package com.letmecare.housecare.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.letmecare.housecare.fragment.FutureWorkFragment;
import com.letmecare.housecare.fragment.PastWorkFragment;

public class ViewPagerWorkListAdapter extends FragmentStatePagerAdapter {

    public ViewPagerWorkListAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PastWorkFragment();
        }
        return new FutureWorkFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0: title = "QUÁ KHỨ";
                break;
            case 1: title = "SẮP XẢY RA";
                break;
        }
        return title;
    }
}
