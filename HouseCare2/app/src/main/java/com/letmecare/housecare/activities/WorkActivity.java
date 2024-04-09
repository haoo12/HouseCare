package com.letmecare.housecare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.letmecare.housecare.R;
import com.letmecare.housecare.models.Work;

public class WorkActivity extends AppCompatActivity {

    SQLiteDatabase mydatabase;

    TextView work;

    int idCV;
    int idValue;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        work = findViewById(R.id.tv_work);

        SharedPreferences preferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        idCV = preferences.getInt("idCV", -1);
        idValue = preferences.getInt("IdUser", -1);

//        Toast.makeText(this, Integer.toString(idValue), Toast.LENGTH_SHORT).show();

        mydatabase = openOrCreateDatabase("HouseCare.db", Context.MODE_PRIVATE, null);

        Cursor cursor = mydatabase.query("chitietcv", null, null, null, null, null, null);
        cursor.moveToNext();

        while (!cursor.isAfterLast()) {

            if(idCV == cursor.getInt(1)) {
                work.setText(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void onBackWork(View view) {
        onBackPressed();
    }

    public void onDelete(View view) {
//        StringBuilder data = new StringBuilder();
        //
        Cursor cursor1 = mydatabase.query("work", null, "id = ?", new String[]{String.valueOf(idCV)}, null, null, null);
        cursor1.moveToNext();

        ContentValues contentValues = new ContentValues();

//        while (!cursor1.isAfterLast()) {
//            cursor1.moveToNext();
//        }


//        data.append(cursor1.getInt(0)).append(cursor1.getString(1)).append(cursor1.getString(2)).append(cursor1.getString(3)).append(cursor1.getString(4)).append(cursor1.getString(5)).append(idValue).append(cursor1.getString(7));

//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();


        contentValues.put("id", cursor1.getInt(0));
        contentValues.put("ngaybatdau", cursor1.getString(1));
        contentValues.put("ngayketthuc", cursor1.getString(2));
        contentValues.put("loai", cursor1.getString(3));
        contentValues.put("diachi", cursor1.getString(4));
        contentValues.put("luachon", cursor1.getString(5));
        contentValues.put("iduser", cursor1.getInt(6));
        contentValues.put("tong", cursor1.getString(7));

        cursor1.close();


        mydatabase.insert("workdelete", null, contentValues);
//
//
        mydatabase.delete("work", "id = ?", new String[]{String.valueOf(idCV)});
        mydatabase.delete("chitietcv", "id = ?", new String[]{String.valueOf(idCV)});

        onBackPressed();
    }
}