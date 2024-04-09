package com.letmecare.housecare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.letmecare.housecare.R;
import com.letmecare.housecare.databinding.ActivityInformationUserBinding;

public class InformationUserActivity extends AppCompatActivity {

    SQLiteDatabase mydatabase;
    ActivityInformationUserBinding binding;

    int idValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInformationUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Local stogare
        // Lấy ra đối tượng SharedPreferences
        SharedPreferences preferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        // Đọc dữ liệu
        idValue = preferences.getInt("IdUser", -1);

        mydatabase = openOrCreateDatabase("HouseCare.db", Context.MODE_PRIVATE, null);

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
                binding.tvUser.setText(cursor.getString(1));
                binding.tvName.setText(cursor.getString(1));
                binding.tvSDT.setText(cursor.getString(3));
                binding.tvEmail.setText(cursor.getString(4));
                binding.tvDate.setText(cursor.getString(5));
                binding.tvDiachi.setText(cursor.getString(6));
                binding.tvGioitinh.setText(cursor.getString(7));

            }
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void btnUpdate(View view) {

        ContentValues newValues = new ContentValues();

        newValues.put("sdt", (String) null);
        newValues.put("thudientu", (String) null);
        newValues.put("ngaysinh", (String) null);
        newValues.put("diachi", (String) null);
        newValues.put("gioitinh", (String) null);

        if(!binding.tvSDT.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            newValues.put("sdt", binding.tvSDT.getText().toString());
        }

        if(!binding.tvEmail.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            newValues.put("thudientu", binding.tvEmail.getText().toString());
        }

        if(!binding.tvDate.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            newValues.put("ngaysinh", binding.tvDate.getText().toString());
        }

        if(!binding.tvDiachi.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            newValues.put("diachi", binding.tvDiachi.getText().toString());
        }

        if(!binding.tvGioitinh.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            newValues.put("gioitinh", binding.tvGioitinh.getText().toString());
        }

        if(mydatabase.update("user", newValues, "id = ?", new String[]{Integer.toString(idValue)}) > 0) {
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackUser(View view) {
        onBackPressed();
    }
}