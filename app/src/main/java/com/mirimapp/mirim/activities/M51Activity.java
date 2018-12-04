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
import com.dahyeon.mirim.R;
import com.mirimapp.mirim.util.BaseActivity;

public class M51Activity extends BaseActivity {

    EditText now_passwd;
    EditText new_passwd;
    EditText new_passwdck;

    String s_now = "";
    String s_new = "";
    String s_newck = "";

    Boolean be_passwd_equal = false; //비밀번호 확인과 일치하지 않음
    Boolean be_passwd_regex = false; //비밀번호 정규식 안맞음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m5_1);
        setTitle("비밀번호 변경");

        Button check = findViewById(R.id.m51_btn_ck); //확인 버튼
        Button cancel = findViewById(R.id.m51_btn_can); //취소 버튼

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

        Button logout = findViewById(R.id.drawer_btn_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeToken(true);
                removeToken(false);
                startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                finish();
            }
        });

        check.setOnClickListener(new View.OnClickListener() { //확인 버튼
            @Override
            public void onClick(View v) {
               now_passwd = findViewById(R.id.now_password);
               new_passwd = findViewById(R.id.new_password);
               new_passwdck = findViewById(R.id.new_passwordck);

               s_now = now_passwd.getText().toString();
               s_new = new_passwd.getText().toString();
               s_newck = new_passwdck.getText().toString();

                if (s_new.matches("^[a-zA-Z0-9_*]{5,12}$")) { //비밀번호 정규표현식 검사(영 대소문자, 숫자, _, * 특수기호로 이루어진 5~12글자) - true일 때
                    if (s_new.equals( s_newck)) { //&비밀번호, 비밀번호 확인이 일치할 때 - true,true
                        be_passwd_regex = true;
                        be_passwd_equal = true;
                    } else{
                        be_passwd_equal = false;
                    }
                } else {
                    be_passwd_regex = false;
                }

                if(be_passwd_equal&&be_passwd_regex){
                    //DB 처리
                    finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() { //취소 버튼
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
