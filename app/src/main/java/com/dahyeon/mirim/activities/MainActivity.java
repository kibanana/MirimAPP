package com.dahyeon.mirim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.dahyeon.mirim.R;

public class MainActivity extends AppCompatActivity {

    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton menu1 = findViewById(R.id.menu_btn1);
        ImageButton menu2 = findViewById(R.id.menu_btn2);
        ImageButton menu3 = findViewById(R.id.menu_btn3);
        ImageButton menu4 = findViewById(R.id.menu_btn4);
        ImageButton menu5 = findViewById(R.id.menu_btn5);

        btn_logout = findViewById(R.id.btn_out); //로그아웃 버튼

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu1SchoolActivity.class);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu2DomitoryActivity.class);
                startActivity(intent);
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu3BoardActivity.class);
                startActivity(intent);
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu4MarketActivity.class);
                startActivity(intent);
            }
        });

        menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu5MypageActivity.class);
                startActivity(intent);
            }
        });

    }

}
