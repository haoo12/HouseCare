package com.letmecare.housecare.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.letmecare.housecare.R;
import com.letmecare.housecare.adapters.ListViewWorkAdapter;
import com.letmecare.housecare.models.Work;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link PastWorkFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class PastWorkFragment extends Fragment {

    ListView lv;
    ListViewWorkAdapter listViewWorkAdapter;
    List<Work> list;

    SQLiteDatabase mydatabase;

    int idValue;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public PastWorkFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment PastWorkFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static PastWorkFragment newInstance(String param1, String param2) {
//        PastWorkFragment fragment = new PastWorkFragment();
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
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_past_work, container, false);

        mydatabase = requireContext().openOrCreateDatabase("HouseCare.db", Context.MODE_PRIVATE, null);

        SharedPreferences preferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();

        // Đọc dữ liệu
        idValue = preferences.getInt("IdUser", -1);
//        Toast.makeText(requireContext(), Integer.toString(idValue), Toast.LENGTH_SHORT).show();


        lv = view.findViewById(R.id.lv_work);
        listViewWorkAdapter = new ListViewWorkAdapter(requireContext(), R.layout.item_work_past, getWorkList());
        lv.setAdapter(listViewWorkAdapter);

//        try {
//            String sql = "CREATE TABLE workdelete(id INTEGER PRIMARY KEY, ngaybatdau TEXT, ngayketthuc TEXT, loai TEXT, diachi TEXT, luachon TEXT, iduser INTEGER, tong TEXT)";
//            mydatabase.execSQL(sql);
//            Toast.makeText(requireContext(), "Tạo bảng workdelete thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(requireContext(), "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
//        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewWorkAdapter.updateData(getWorkList());
//        Toast.makeText(requireContext(), "Xin chào nha", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private List<Work> getWorkList() {

        List<Work> list1 = new ArrayList<>();

        Cursor cursor = mydatabase.query("workdelete", null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
//            Toast.makeText(requireContext(), Integer.toString(idValue), Toast.LENGTH_SHORT).show();


            if (idValue == cursor.getInt(6)) {
//                Toast.makeText(requireContext(), Integer.toString(cursor.getInt(6)), Toast.LENGTH_SHORT).show();
                list1.add(new Work(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(7)));
            }

            cursor.moveToNext();
        }
        cursor.close();


//        list1.add(new Work("11", "11", "11", "11", "11", " aa"));
//        list1.add(new Work("11", "11", "11", "11", "11", " aa"));
//        list1.add(new Work("11", "11", "11", "11", "11", " aa"));


        return list1;
    }
}