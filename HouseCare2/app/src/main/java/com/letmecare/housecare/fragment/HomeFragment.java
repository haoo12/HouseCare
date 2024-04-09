package com.letmecare.housecare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.letmecare.housecare.R;
import com.letmecare.housecare.adapters.OptionAdapter;
import com.letmecare.housecare.adapters.PhotoAdapter;
import com.letmecare.housecare.databinding.FragmentHomeBinding;
import com.letmecare.housecare.models.Option;

import java.util.ArrayList;
import java.util.List;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link HomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    PhotoAdapter photoAdapter;

    RecyclerView recyclerView;
    OptionAdapter optionAdapter;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        photoAdapter = new PhotoAdapter(getContext(), getListPhoto());
        binding.viewPagerPhoto.setAdapter(photoAdapter);
        binding.circleImg.setViewPager(binding.viewPagerPhoto);

        photoAdapter.registerDataSetObserver(binding.circleImg.getDataSetObserver());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 3);
        binding.rcv.setLayoutManager(gridLayoutManager);
        optionAdapter = new OptionAdapter(requireContext());
        optionAdapter.setData(getListOption());
        binding.rcv.setAdapter(optionAdapter);

        return view;

    }

    private List<Integer> getListPhoto() {
        List<Integer> listPhoto = new ArrayList<>();
        listPhoto.add(R.drawable.photo1);
        listPhoto.add(R.drawable.photo2);
        listPhoto.add(R.drawable.photo3);
        listPhoto.add(R.drawable.photo4);
        listPhoto.add(R.drawable.photo5);

        return listPhoto;
    }
    private List<Option> getListOption() {
        List<Option> listOption = new ArrayList<>();
        listOption.add(new Option(R.drawable.opt1, "Riêng lẻ"));
        listOption.add(new Option(R.drawable.opt1, "Định kỳ"));
        listOption.add(new Option(R.drawable.opt2, "Riêng lẻ cao cấp"));
        listOption.add(new Option(R.drawable.opt2, "Định kỳ cao cấp"));
        listOption.add(new Option(R.drawable.opt3, "Máy lạnh"));
        listOption.add(new Option(R.drawable.opt4, "Tủ lạnh"));
        listOption.add(new Option(R.drawable.opt5, "Máy giặt"));
        listOption.add(new Option(R.drawable.opt6, "Công nghiệp"));
        listOption.add(new Option(R.drawable.opt7, "Côn trùng"));

        return listOption;
    }
}