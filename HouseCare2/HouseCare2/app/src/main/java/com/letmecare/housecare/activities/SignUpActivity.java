package com.letmecare.housecare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.letmecare.housecare.R;
import com.letmecare.housecare.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mydatabase = openOrCreateDatabase("HouseCare.db", MODE_PRIVATE, null);
////
//        try {
//            String sql = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, taikhoan TEXT, matkhau TEXT, sdt TEXT, thudientu TEXT, ngaysinh TEXT, diachi TEXT, gioitinh TEXT)";
////            String sql1 = "DROP TABLE IF EXISTS user";
//            mydatabase.execSQL(sql);
////            Toast.makeText(this, "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
////            Toast.makeText(this, "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
//        }


        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(isRegistered()) {
                    Toast.makeText(SignUpActivity.this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();

                }
                else if(!binding.editTextPassword.getText().toString().equals(binding.editTextPasswordAgain.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", (String) null);
                    contentValues.put("taikhoan", binding.editTextUser.getText().toString());
                    contentValues.put("matkhau", binding.editTextPassword.getText().toString());

                    contentValues.put("sdt", (String) null);
                    contentValues.put("thudientu", (String) null);
                    contentValues.put("ngaysinh", (String) null);
                    contentValues.put("diachi", (String) null);
                    contentValues.put("gioitinh", (String) null);


                    if(mydatabase.insert("user", null, contentValues) != -1) {
                        Toast.makeText(SignUpActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public void clickTextSignUp(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }
    public  boolean isRegistered() {

//        boolean check;

        Cursor cursor = mydatabase.query("user", null, null, null, null, null, null);
        cursor.moveToNext();

        while (!cursor.isAfterLast()) {
            String user = binding.editTextUser.getText().toString();
            if(user.equals(cursor.getString(1) )){
                return true;
            }
            cursor.moveToNext();
        }
//        check = false;
        cursor.close();

        return false;
    }
}