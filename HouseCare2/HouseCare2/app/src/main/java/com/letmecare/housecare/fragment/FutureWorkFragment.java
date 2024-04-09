package com.letmecare.housecare.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.letmecare.housecare.R;
import com.letmecare.housecare.activities.LoginActivity;
import com.letmecare.housecare.activities.MainActivity;
import com.letmecare.housecare.activities.WorkActivity;
import com.letmecare.housecare.adapters.ListViewWorkAdapter;
import com.letmecare.housecare.models.Work;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link FutureWorkFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class FutureWorkFragment extends Fragment {

    ListView lv;
    ListViewWorkAdapter listViewWorkAdapter;
    List<Work> list;

    SQLiteDatabase mydatabase;

    int idValue;

    List<Integer> idCV;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public FutureWorkFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment FutureWorkFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static FutureWorkFragment newInstance(String param1, String param2) {
//        FutureWorkFragment fragment = new FutureWorkFragment();
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
        View view = inflater.inflate(R.layout.fragment_future_work, container, false);

        mydatabase = requireContext().openOrCreateDatabase("HouseCare.db", Context.MODE_PRIVATE, null);

//
//        try {
//            String sql = "CREATE TABLE work(id INTEGER PRIMARY KEY, ngaybatdau TEXT, ngayketthuc TEXT, loai TEXT, diachi TEXT, luachon TEXT, iduser INTEGER, tong TEXT)";
//            mydatabase.execSQL(sql);
////            Toast.makeText(requireContext(), "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
////            Toast.makeText(requireContext(), "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
//        }
        SharedPreferences preferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Đọc dữ liệu
        idValue = preferences.getInt("IdUser", -1);

        lv = view.findViewById(R.id.lv_work);
        listViewWorkAdapter = new ListViewWorkAdapter(requireContext(), R.layout.item_work, getWorkList());
        lv.setAdapter(listViewWorkAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                editor.putInt("idCV", idCV.get(i));
                editor.apply();

                startActivity(new Intent(requireContext(), WorkActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewWorkAdapter.updateData(getWorkList());
//        Toast.makeText(requireContext(), "Xin chào nha", Toast.LENGTH_SHORT).show();
    }

    private List<Work> getWorkList() {

        List<Work> list1 = new ArrayList<>();
        idCV = new ArrayList<>();

        Cursor cursor = mydatabase.query("work", null, null, null, null, null, null);
        cursor.moveToNext();

        while (!cursor.isAfterLast()) {

//            if(user.equals(cursor.getString(1)) && password.equals(cursor.getString(2))){
//                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            }
            if (idValue == cursor.getInt(6)) {
                idCV.add(cursor.getInt(0));
                list1.add(new Work(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(7)));
            }
            cursor.moveToNext();
        }
        cursor.close();

//        list1.add(new Work("11", "11", "11", "11", "11"));
//        list1.add(new Work("11", "11", "11", "11", "11"));
//        list1.add(new Work("11", "11", "11", "11", "11"));
//        list1.add(new Work("11", "11", "11", "11", "11"));
//        list1.add(new Work("11", "11", "11", "11", "11"));
//        list1.add(new Work("11", "11", "11", "11", "11"));

        return list1;
    }
}