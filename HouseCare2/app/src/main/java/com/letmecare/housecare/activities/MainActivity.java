package com.letmecare.housecare.activities;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.letmecare.housecare.R;
import com.letmecare.housecare.adapters.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Anhxa();

        int[] tabIcons = {
                R.drawable.outline_home_24,
                R.drawable.outline_calendar_today_24,
                //R.drawable.baseline_account_balance_wallet_24,
                //R.drawable.baseline_savings_24,
                R.drawable.outline_person_24
        };

        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for(int i = 0; i < tabIcons.length; i++) {
            Objects.requireNonNull(tabLayout.getTabAt(i)).setIcon(tabIcons[i]);
        }

        ColorStateList tabColors = ContextCompat.getColorStateList(this, R.color.tabs_color);
        tabLayout.setTabIconTint(tabColors);
        tabLayout.setTabTextColors(tabColors);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab != null) {
                    tab.setText(getTabText().get(tab.getPosition()));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setText(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Thực hiện hành động khi tab được chọn lại (nếu cần)
            }
        });


    }

    private void Anhxa() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    private List<String> getTabText() {

        List<String> Array = new ArrayList<>();
        Array.add("Trang chủ");
        Array.add("Công việc");
        //Array.add("Thanh toán");
        //Array.add("Ưu đãi");
        Array.add("Tài khoản");

        return Array;
    }

}