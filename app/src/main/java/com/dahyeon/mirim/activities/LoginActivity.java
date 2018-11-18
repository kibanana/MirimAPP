package com.dahyeon.mirim.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.dahyeon.mirim.R;

public class LoginActivity extends AppCompatActivity {

    //<로그인 페이지>
    EditText email_in; //이메일 입력
    EditText password_in; //패스워드 입력

    CheckBox ck_in; //자동로그인 체크박스
    Button btn_in; //로그인 버튼
    TextView text_up; //'아직 회원이 아니신가요?' 텍스트뷰
    //TextView forgot_passwd; //비밀번호 찾기 텍스트뷰 => 페이지 아직 안만들어져있음

    TextView be_in; //로그인 안되는 이유 나타내는 텍스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ck_in = findViewById(R.id.ck_auto);
        btn_in = findViewById(R.id.btn_in);
        text_up = findViewById(R.id.text_in);

        be_in = findViewById(R.id.because_in);

        btn_in.setOnClickListener(new View.OnClickListener() { //로그인 버튼 - DB에서 맞는지 체크 필요
            @Override
            public void onClick(View v) {
                //DB에서 체크
                //DB에 없는 이메일, 비밀번호라면 be_in.setText("존재하지 않는 이메일, 비밀번호입니다");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        text_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //회원가입 해주세요
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_in = findViewById(R.id.email_in);
                password_in = findViewById(R.id.password_in);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
