package com.mirimapp.mirim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.dahyeon.mirim.R;

public class DrawerActivity extends AppCompatActivity {

    TextView drawer_text;
    Button drawer_btn_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawer_text = findViewById(R.id.drawer_text); //~님 표시하는 텍스트뷰
        drawer_btn_out = findViewById(R.id.drawer_btn_out); //로그아웃 버튼

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
            }
        });

        menu_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu2DomitoryActivity.class);
                startActivity(intent);
            }
        });

        menu_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu3BoardActivity.class);
                startActivity(intent);
            }
        });

        menu_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu4MarketActivity.class);
                startActivity(intent);
            }
        });

        menu_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu5MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}
