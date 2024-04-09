package com.letmecare.housecare.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.letmecare.housecare.R;

public class IntroActivity extends AppCompatActivity {

    SQLiteDatabase mydatabase;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mydatabase = openOrCreateDatabase("HouseCare.db", MODE_PRIVATE, null);

        try {
            String sql = "CREATE TABLE workdelete(id INTEGER PRIMARY KEY, ngaybatdau TEXT, ngayketthuc TEXT, loai TEXT, diachi TEXT, luachon TEXT, iduser INTEGER, tong TEXT)";
            mydatabase.execSQL(sql);
//            Toast.makeText(this, "Tạo bảng workdelete thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
//            Toast.makeText(this, "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
        }

        try {
            String sql = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, taikhoan TEXT, matkhau TEXT, sdt TEXT, thudientu TEXT, ngaysinh TEXT, diachi TEXT, gioitinh TEXT)";
//            String sql1 = "DROP TABLE IF EXISTS user";
            mydatabase.execSQL(sql);
//            Toast.makeText(this, "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
//            Toast.makeText(this, "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
        }

        try {
            String sql = "CREATE TABLE work(id INTEGER PRIMARY KEY, ngaybatdau TEXT, ngayketthuc TEXT, loai TEXT, diachi TEXT, luachon TEXT, iduser INTEGER, tong TEXT)";
            mydatabase.execSQL(sql);
//            Toast.makeText(requireContext(), "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
//            Toast.makeText(requireContext(), "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
        }
        try {
            String sql = "CREATE TABLE chitietcv(id PRIMARY KEY,idchitietcv INTEGER, lichcv TEXT)";
            mydatabase.execSQL(sql);
//            Toast.makeText(this, "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
//            Toast.makeText(this, "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
        }

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }
        },2000);


    }
}