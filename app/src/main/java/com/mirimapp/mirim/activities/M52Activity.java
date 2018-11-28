package com.mirimapp.mirim.activities;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.dahyeon.mirim.R;

public class M52Activity extends AppCompatActivity {

    EditText new_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m5_2);
        setTitle("닉네임 변경");

        TextView now_nickname = findViewById(R.id.now_nickname); //여기 아무것도 안하면 빈걸로 되어있음. DB에서 닉네임 받아와서 setText 해줘야함


        Button check = findViewById(R.id.m52_btn_ck); //확인 버튼
        Button cancel = findViewById(R.id.m52_btn_can); //취소 버튼

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

        Button menu_btn2 = findViewById(R.id.button_drawer_to_dormitory_activity);
        Button menu_btn3 = findViewById(R.id.button_drawer_to_board_activity);
        Button menu_btn4 = findViewById(R.id.button_drawer_to_market_activity);
        Button menu_btn5 = findViewById(R.id.button_drawer_to_mypage_activity);

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
                new_nickname = findViewById(R.id.new_nickname);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
