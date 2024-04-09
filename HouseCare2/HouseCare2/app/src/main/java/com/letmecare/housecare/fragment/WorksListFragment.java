package com.letmecare.housecare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.letmecare.housecare.R;
import com.letmecare.housecare.adapters.ViewPagerAdapter;
import com.letmecare.housecare.adapters.ViewPagerWorkListAdapter;

import java.util.Objects;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link WorksListFragment#newInstance} factory method to
// * create an instance of this fragment.
// *
// */
public class WorksListFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment WorksListFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static WorksListFragment newInstance(String param1, String param2) {
//        WorksListFragment fragment = new WorksListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    public WorksListFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_works_list, container, false);

        tabLayout = view.findViewById(R.id.tabLayoutWorkList);
        viewPager = view.findViewById(R.id.viewPagerWorkList);

        ViewPagerWorkListAdapter viewPagerWorkListAdapter= new ViewPagerWorkListAdapter(requireActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerWorkListAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        Objects.requireNonNull(tabLayout.getTabAt(0)).setText("QUÁ KHỨ");
//        Objects.requireNonNull(tabLayout.getTabAt(1)).setText("SẮP XẢY RA");

        return view;
    }

}