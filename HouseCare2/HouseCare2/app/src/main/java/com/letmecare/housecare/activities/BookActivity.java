package com.letmecare.housecare.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.letmecare.housecare.R;
import com.letmecare.housecare.adapters.ListVIewAdapter;
import com.letmecare.housecare.adapters.ListViewInformationAdapter;
import com.letmecare.housecare.databinding.ActivityBookBinding;
import com.letmecare.housecare.models.Day;
import com.letmecare.housecare.models.Information;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

public class BookActivity extends AppCompatActivity {

    ListView listView, listView_2;
    int day, month, year;
    int hour, minute;

    RadioButton btnDays, btnWeeks;
    RadioGroup btnGrp;

    ActivityBookBinding binding;

    TextView time, hours;

    List<Day> list, listDay;
    List<Information> list1;

    int hToNum = 0;

//    View RadioFormView;

    String hr;

    SQLiteDatabase mydatabase;

    long value;

    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        list = getDay();
        listView = findViewById(R.id.lv_book);
        ListVIewAdapter listVIewAdapter = new ListVIewAdapter(this,R.layout.item_day, list);
        listView.setAdapter(listVIewAdapter);

        list1 = getInformaion();
        listView_2 = findViewById(R.id.lv_information);
        ListViewInformationAdapter listViewInformationAdapter = new ListViewInformationAdapter(this, R.layout.item_information, getInformaion());
        listView_2.setAdapter(listViewInformationAdapter);


        btnDays = findViewById(R.id.btnday);
        btnWeeks = findViewById(R.id.btnweek);
        btnGrp = findViewById(R.id.btnGrp);

        btnGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                binding.total.setText("0K VND");

                if(i == R.id.btnday) {
                    listVIewAdapter.updateDataDay(list, "");

                    ViewGroup.LayoutParams params = listView.getLayoutParams();
                    params.height = 150; // Đặt giá trị chiều cao mong muốn
                    listView.setLayoutParams(params);
                }
                if (i == R.id.btnweek) {
                    listVIewAdapter.updateDataWeek(list, "");

                    ViewGroup.LayoutParams params = listView.getLayoutParams();
                    params.height = 800; // Đặt giá trị chiều cao mong muốn
                    listView.setLayoutParams(params);

                }
            }
        });

        //Calendar
        Calendar calendar = Calendar.getInstance();
         day = calendar.get(Calendar.DAY_OF_MONTH);
         month = calendar.get(Calendar.MONTH);
         year = calendar.get(Calendar.YEAR);

         binding.tvDayStart.setText(String.format("%d/%d/%d", day, month + 1, year));
         binding.tvDayEnd.setText(String.format("%d/%d/%d", day, month + 3, year));


        hour = calendar.get(Calendar.HOUR);
         minute = calendar.get(Calendar.MINUTE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                LayoutInflater inflater = LayoutInflater.from(BookActivity.this);
                View timeFormView = inflater.inflate(R.layout.dialog_time, null);

                time = timeFormView.findViewById(R.id.time_start);
                hours = timeFormView.findViewById(R.id.tv_hours);
                time.setText(String.format("%d: %d", hour, minute));

                new AlertDialog.Builder(BookActivity.this)
                        .setTitle(getDay().get(position).getDay())
                        .setView(timeFormView)
                        .setNegativeButton("Hủy", null)
                        .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

//                                Toast.makeText(BookActivity.this, hours.getText().toString(), Toast.LENGTH_SHORT).show();
                                listVIewAdapter.updateData(list, position, time.getText() +" ~ "+ hours.getText());

                                String[] h = hours.getText().toString().split(" ");
                                hToNum += Long.parseLong(h[0]);

                                DecimalFormat decimalFormat = new DecimalFormat("###,###");
                                String formattedNumber = decimalFormat.format(hToNum * 700000);

                                binding.total.setText(formattedNumber + " VND");

                            }
                        })
                        .show();


            }
        });

        listView_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int p, long l) {

                LayoutInflater inflater = LayoutInflater.from(BookActivity.this);
                View InformationFormView = inflater.inflate(R.layout.dialog_information, null);

                EditText editText = InformationFormView.findViewById(R.id.edtInformation);

                new AlertDialog.Builder(BookActivity.this)
                        .setTitle(getInformaion().get(p).getTitle())
                        .setView(InformationFormView)
                        .setNegativeButton("Hủy", null)
                        .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                listViewInformationAdapter.updateData(list1, p, editText.getText().toString());

//                                Toast.makeText(BookActivity.this, editText.getText(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        // Local Storage
        SharedPreferences preferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        // Đọc dữ liệu
        int idValue = preferences.getInt("IdUser", -1);


        mydatabase = openOrCreateDatabase("HouseCare.db", MODE_PRIVATE, null);
//
//        try {
//            String sql = "CREATE TABLE work(id INTEGER PRIMARY KEY AUTOINCREMENT, ngaybatdau TEXT, ngayketthuc TEXT, loai TEXT, diachi TEXT, luachon TEXT, iduser INTEGER, tong TEXT)";
//            String sql1 = "DROP TABLE IF EXISTS work";
//
//            mydatabase.execSQL(sql);
////            Toast.makeText(this, "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
////            Toast.makeText(this, "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
//        }

//        try {
//            String sql = "CREATE TABLE chitietcv(id PRIMARY KEY,idchitietcv INTEGER, lichcv TEXT)";
//            mydatabase.execSQL(sql);
////            Toast.makeText(this, "Tạo bảng thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
////            Toast.makeText(this, "Lỗi tạo bảng", Toast.LENGTH_SHORT).show();
//        }

        binding.creactDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str = listVIewAdapter.getBookTimeString();
//                Toast.makeText(BookActivity.this, str, Toast.LENGTH_SHORT).show();

                String loai = "";
                String luachon = "";

                luachon = preferences.getString("Loai", "");

                if(binding.btnday.isChecked()) {
                    loai = binding.btnday.getText().toString();
                }
                if(binding.btnweek.isChecked()) {
                    loai = binding.btnweek.getText().toString();
                }

                ContentValues contentValues = new ContentValues();

//              contentValues.put("idCV", (String) null);
                contentValues.put("id", (String) null);
                contentValues.put("ngaybatdau", binding.tvDayStart.getText().toString());
                contentValues.put("ngayketthuc", binding.tvDayEnd.getText().toString());
                contentValues.put("loai", loai);
                contentValues.put("diachi", listViewInformationAdapter.getLocation(list1, 1));
                contentValues.put("luachon", luachon);
                contentValues.put("iduser", idValue);
                contentValues.put("tong", binding.total.getText().toString());

                value = mydatabase.insert("work", null, contentValues);

//                Toast.makeText(BookActivity.this, Long.toString(value), Toast.LENGTH_SHORT).show();
                if(value != -1) {
                    Toast.makeText(BookActivity.this, "Tạo dịch vụ thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookActivity.this, "Tạo dịch vụ thất bại", Toast.LENGTH_SHORT).show();
                }

                ContentValues contentValues1 = new ContentValues();

                contentValues1.put("id", (String) null);
                contentValues1.put("idchitietcv", value);
                contentValues1.put("lichcv", str);
                
                if(mydatabase.insert("chitietcv", null, contentValues1) != -1) {
//                    Toast.makeText(BookActivity.this, "Thêm công việc thành công", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(BookActivity.this, "Thêm công việc thất bại", Toast.LENGTH_SHORT).show();
                }


//                Toast.makeText(BookActivity.this,listViewInformationAdapter.getLocation(list1, 1) , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Information> getInformaion() {
        List<Information> list = new ArrayList<>();

        list.add(new Information(R.drawable.baseline_girl_24, "Ngẫu nhiên"));
        list.add(new Information(R.drawable.baseline_location_on_24, "Vị trí"));
        list.add(new Information(R.drawable.baseline_house_24, "Số nhà/ Số căn hộ"));
        list.add(new Information(R.drawable.baseline_edit_calendar_24, "Ghi rõ yêu cầu công việc"));
        list.add(new Information(R.drawable.baseline_savings_24, "Chọn mã khuyến mãi"));


        return list;
    }

    public void onBack(View view) {
        startActivity(new Intent(BookActivity.this, MainActivity.class));
    }

    public List<Day> getDay() {

        List<Day> list = new ArrayList<>();

        list.add(new Day("Thứ 2", R.drawable.baseline_add_circle_24, ""));
        list.add(new Day("Thứ 3", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Thứ 4", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Thứ 5", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Thứ 6", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Thứ 7", R.drawable.baseline_add_circle_24,""));
        list.add(new Day("Chủ nhật", R.drawable.baseline_add_circle_24,""));


        return list;

    }

    public void onDateEnd(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(BookActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        binding.tvDayEnd.setText(String.format("%d/%d/%d", i2, i1 + 1, i));

                    }
                }, year, month, day
        );
        datePickerDialog.show();
    }

    public void onDateStart(View view) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(BookActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        binding.tvDayStart.setText(String.format("%d/%d/%d", i2, i1 + 1, i));

                    }
                }, year, month, day
        );
        datePickerDialog.show();

    }
    public void onTimeStart(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(BookActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time.setText(String.format("%d: %d", i, i1));
                    }
                }, hour, minute, true
        );
        timePickerDialog.show();
    }

    public void onTimeEnd(View view) {

        LayoutInflater inflater = LayoutInflater.from(BookActivity.this);
        View RadioFormView = inflater.inflate(R.layout.dialog_hours, null);

        RadioGroup radioGroup = RadioFormView.findViewById(R.id.radioBtnHours);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int i) {

                if(i == R.id.radio1) {
                    hr = "2 Giờ";
                }
                if(i == R.id.radio2) {
                    hr = "3 Giờ";

                }
                if(i == R.id.radio3) {
                    hr = "4 Giờ";

                }
                if(i == R.id.radio4) {
                    hr = "5 Giờ";

                }
                if(i == R.id.radio5) {
                    hr = "6 Giờ";

                }
                if(i == R.id.radio6) {
                    hr = "7 Giờ";

                }
                if(i == R.id.radio7) {
                    hr = "8 Giờ";

                }

            }
        });

        new AlertDialog.Builder(BookActivity.this)
                .setTitle("Thời lượng")
                .setView(RadioFormView)
                .setNegativeButton("Hủy", null)
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hours.setText(hr);
                    }
                })
                .show();
    }
}