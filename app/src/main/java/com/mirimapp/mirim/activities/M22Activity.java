package com.mirimapp.mirim.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.dahyeon.mirim.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class M22Activity extends AppCompatActivity {

    int mYear_start, mMonth_start, mDay_start;
    int mYear_end, mMonth_end, mDay_end;

    TextView mTxtDate_start;
    TextView mTxtDate_end;

    EditText be_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2_2);
        setTitle("외박일지");

        Button check = findViewById(R.id.m22_btn_ck); //확인 버튼
        Button cancel = findViewById(R.id.m22_btn_can); //취소 버튼

        //텍스트뷰 2개 연결
        mTxtDate_start = (TextView)findViewById(R.id.txtdate_start);
        mTxtDate_end = (TextView)findViewById(R.id.txtdate_end);

        //현재 날짜와 시간을 가져오기위한 Calendar 인스턴스 선언
        Calendar cal = new GregorianCalendar();
        mYear_start = cal.get(Calendar.YEAR);
        mMonth_start = cal.get(Calendar.MONTH);
        mDay_start = cal.get(Calendar.DAY_OF_MONTH);

        mYear_end = cal.get(Calendar.YEAR);
        mMonth_end = cal.get(Calendar.MONTH);
        mDay_end = cal.get(Calendar.DAY_OF_MONTH);

        UpdateNow();//화면에 텍스트뷰에 업데이트 해줌.


        ImageButton buttonOpen = (ImageButton) findViewById(R.id.menu) ;
        buttonOpen.setOnClickListener(new Button.OnClickListener() {
            @Override public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer) ;
                if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.openDrawer(Gravity.RIGHT) ;
                }
                else if(drawer.isDrawerOpen(Gravity.RIGHT)){
                    drawer.closeDrawer(Gravity.RIGHT);
                }
            }
        });

        Button menu_btn1 = findViewById(R.id.menu1);
        Button menu_btn2 = findViewById(R.id.menu2);
        Button menu_btn3 = findViewById(R.id.menu3);
        Button menu_btn4 = findViewById(R.id.menu4);
        Button menu_btn5 = findViewById(R.id.menu5);

        menu_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu1SchoolActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        menu_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu2DomitoryActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        menu_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu3BoardActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        menu_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu4MarketActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        menu_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu5MypageActivity.class);
                startActivity(intent);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                be_v = findViewById(R.id.text_be); //제출버튼 누르고 값 가져옴(그 전에 가져오면 공백일 수 있음...)
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() { //취소 버튼
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void mOnClick(View v){
        switch(v.getId()){

            //날짜 대화상자 버튼이 눌리면 대화상자를 보여줌
            case R.id.btnchangedate_start:
                //여기서 리스너도 등록함
                new DatePickerDialog(M22Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, handler, mYear_start, mMonth_start, mDay_start).show();
                break;

            case R.id.btnchangedate_end:
                //여기서 리스너도 등록함
                new DatePickerDialog(M22Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, handler, mYear_end, mMonth_end, mDay_end).show();
                break;
        }

    }

    //날짜 대화상자 리스너 부분

    DatePickerDialog.OnDateSetListener handler = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //사용자가 입력한 값을 가져온뒤
            mYear_start = year;
            mMonth_start = month;
            mDay_start = dayOfMonth;
            //텍스트뷰의 값을 업데이트함
            UpdateNow();
        }
    };

    //텍스트뷰의 값을 업데이트 하는 메소드
    void UpdateNow(){
        mTxtDate_start.setText(String.format("%d/%d/%d", mYear_start, mMonth_start + 1, mDay_start));
        mTxtDate_end.setText(String.format("%d/%d/%d", mYear_end, mMonth_end + 1, mDay_end));
    }
}