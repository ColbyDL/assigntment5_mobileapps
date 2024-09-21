package com.example.assignment05;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateOfBirth extends AppCompatActivity {

    String date;
    CalendarView calendar;
    private Button cancel;
    private Button submit;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_date_of_birth);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cancel = findViewById(R.id.calendar_cancel_button);

        submit = findViewById(R.id.calendar_submit_button);





        long today_date = Instant.now().toEpochMilli();
        long max_date = today_date + 568_024_668_000L;

        calendar = findViewById(R.id.calendar);
        calendar.setDate(today_date);
        calendar.setMaxDate(max_date);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                String m = "";
                String d = "";
                if(month < 10){
                    m = String.format("0%d", month);
                } else {
                    m = String.valueOf(month);
                }

                if (day < 10){
                    d = String.format("0%d", day);
                } else {
                    d = String.valueOf(day);
                }

                date = String.format("%s/%s/%d", m, d, year);
                Log.d("date", date);




            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userInfo = new Intent(DateOfBirth.this, UserInfo.class);
                startActivity(userInfo);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userInfo = new Intent(DateOfBirth.this, UserInfo.class);


                startActivity(userInfo);
                finish();
            }
        });


    }
}