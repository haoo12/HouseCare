package com.letmecare.housecare.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.letmecare.housecare.R;
import com.letmecare.housecare.activities.InformationUserActivity;
import com.letmecare.housecare.activities.LoginActivity;
import com.letmecare.housecare.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link PersonalFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class PersonalFragment extends Fragment {

    ListView listView;
    ArrayAdapter arrayAdapter;

    SharedPreferences.Editor editor;
    SQLiteDatabase mydatabase;
    TextView tvUser;
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public PersonalFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment PersonalFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static PersonalFragment newInstance(String param1, String param2) {
//        PersonalFragment fragment = new PersonalFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

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
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        tvUser = view.findViewById(R.id.tv_user);

        listView = view.findViewById(R.id.lv);
        arrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_activated_1, getItemListView());
        listView.setAdapter(arrayAdapter);

        //Local stogare
        // Lấy ra đối tượng SharedPreferences
        final SharedPreferences[] preferences = {requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE)};

        // Đọc dữ liệu
        int idValue = preferences[0].getInt("IdUser", -1);

//        Toast.makeText(requireContext(), Integer.toString(idValue), Toast.LENGTH_SHORT).show();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i==0){
                        startActivity(new Intent(requireContext(), InformationUserActivity.class));
                    }

//                if(i==6){
//                    delete();
////                    editor.clear();
////                    editor.commit();
//                    Intent intent = new Intent(getActivity(),LoginActivity.class);
//////                    getActivity().finish();
//                    Toast.makeText(getActivity(), "Đằng xuất thành công", Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//                }
            }
        });

        mydatabase = requireActivity().openOrCreateDatabase("HouseCare.db", Context.MODE_PRIVATE, null);

//        try {
//            String sql = "CREATE TABLE user(id INTEGER PRIMARY KEY, taikhoan TEXT, matkhau TEXT)";
//            mydatabase.execSQL(sql);
//            Toast.makeText(requireContext(), "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Toast.makeText(requireContext(),"Lỗi", Toast.LENGTH_SHORT).show();
//        }
        Cursor cursor = mydatabase.query("user", null, null, null, null, null, null);
        cursor.moveToNext();

        while (!cursor.isAfterLast()) {
            if(cursor.getInt(0) == idValue){
                tvUser.setText(cursor.getString(1));
            }
            cursor.moveToNext();
        }
        cursor.close();



        return view;

    }
//    public  void delete(){
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_preferences",Context.MODE_PRIVATE);
//        sharedPreferences.edit().remove("IdUser").commit();
//    }
    private ArrayList<String> getItemListView() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Tài khoản");
        arrayList.add("Thông báo");
        arrayList.add("Cài đặt");
        arrayList.add("Đơn giá");
        arrayList.add("Hỗ trợ");
        arrayList.add("Chính sách");
        arrayList.add("Xóa tài khoản");

        return arrayList;

    }
}