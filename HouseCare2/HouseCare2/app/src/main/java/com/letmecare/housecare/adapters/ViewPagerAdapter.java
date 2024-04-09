package com.letmecare.housecare.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.letmecare.housecare.fragment.DiscountFragment;
import com.letmecare.housecare.fragment.HomeFragment;
import com.letmecare.housecare.fragment.PayFragment;
import com.letmecare.housecare.fragment.PersonalFragment;
import com.letmecare.housecare.fragment.WorksListFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new HomeFragment();
            case 1: return new WorksListFragment();
            //case 2: return new PayFragment();
            //case 3: return new DiscountFragment();
            case 2: return new PersonalFragment();

            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (position == 0) {
            title = "Trang chủ";
            //            case 1: title = "Công việc";
//                break;
//            case 2: title = "Thanh toán";
//                break;
//            case 3: title = "Ưu đãi";
//                break;
//            case 4: title = "Tài khoản";
//                break;
        }
        return title;
    }
}
