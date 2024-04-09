package com.letmecare.housecare.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.letmecare.housecare.R;
import com.letmecare.housecare.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Local storage
        SharedPreferences preferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        mydatabase = openOrCreateDatabase("HouseCare.db", MODE_PRIVATE, null);
//
//        try {
//            String sql = "CREATE TABLE workdelete(id INTEGER PRIMARY KEY, ngaybatdau TEXT, ngayketthuc TEXT, loai TEXT, diachi TEXT, luachon TEXT, iduser INTEGER, tong TEXT)";
//            mydatabase.execSQL(sql);
////            Toast.makeText(this, "Tạo bảng workdelete thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
////            Toast.makeText(this, "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
//        }
//
//        try {
//            String sql = "CREATE TABLE user(id INTEGER PRIMARY KEY, taikhoan TEXT, matkhau TEXT)";
//            mydatabase.execSQL(sql);
////            Toast.makeText(this, "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
////            Toast.makeText(this, "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
//        }

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder data = new StringBuilder();
                Cursor cursor = mydatabase.query("user", null, null, null, null, null, null);
                cursor.moveToNext();

                while (!cursor.isAfterLast()) {
                    String user = binding.editTextUser.getText().toString();
                    String password = binding.editTextPassword.getText().toString();
                    if(user.equals(cursor.getString(1)) && password.equals(cursor.getString(2))){
                        // Đưa id lên local stogare
                        editor.putInt("IdUser", cursor.getInt(0));
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else  {
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng !!!", Toast.LENGTH_SHORT).show();
                       // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
//                    data.append(cursor.getString(0)).append(" ").append(cursor.getString(1)).append(" ").append(cursor.getString(2)).append("/n");
                    cursor.moveToNext();
                }
//                binding.tv.setText(data.toString());
                cursor.close();
            }
        });
    }
    public void clickTextLogin(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }
}