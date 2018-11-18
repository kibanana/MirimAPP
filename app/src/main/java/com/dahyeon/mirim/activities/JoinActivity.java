package com.dahyeon.mirim.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.dahyeon.mirim.R;

public class JoinActivity extends AppCompatActivity  implements View.OnClickListener, Dialog.OnCancelListener{

    final int randomNum = 106254; // 테스트할 6자리 인증번호

    EditText authEmail;
    TextView authBtn;

    /*Dialog에 관련된 필드*/

    LayoutInflater dialog; //LayoutInflater
    View dialogLayout; //layout을 담을 View
    Dialog authDialog; //dialog 객체

    /*카운트 다운 타이머에 관련된 필드*/

    TextView time_counter; //시간을 보여주는 TextView
    EditText emailAuth_number; //인증 번호를 입력 하는 칸
    Button emailAuth_btn; // 인증버튼
    CountDownTimer countDownTimer;
    final int MILLISINFUTURE = 180 * 1000; //총 시간 (180초 = 3분)
    final int COUNT_DOWN_INTERVAL = 1000; //onTick 메소드를 호출할 간격 (1초)


    //↓ 이메일, 비밀번호 정규표현식
    String regex = "^[a-zA-Z0-9_*]{5,12}$"; //비밀번호는 5~12글자 이하, _, * 특수문자만 사용 가능

    //<회원가입 페이지>
    EditText email_up;
    EditText password_up;
    EditText passwordck_up;

    String email_full;
    String passwd_full;
    String passwdck_full;

    Button push_up;  //회원가입 버튼
    TextView text_email; //'이메일로 메일 발송' 텍스트뷰

    TextView text_in; //'회원이시라면 로그인' 텍스트뷰
    TextView be_up; //회원가입 안되는 이유 나타내는 텍스트뷰
    String be_uptext = "";

    //↓ 이메일 인증 안됨, 비밀번호 확인과 일치하지 않음, 비밀번호 정규식 안맞음 등 체크
    Boolean be_email = false; //이메일 인증 안됨
    Boolean be_passwd_equal = false; //비밀번호 확인과 일치하지 않음
    Boolean be_passwd_regex = false; //비밀번호 정규식 안맞음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        authEmail = (EditText) findViewById(R.id.email_up);
        authBtn = (TextView) findViewById(R.id.text_email);
        authBtn.setOnClickListener(this);

        text_in.setOnClickListener(new View.OnClickListener() { //회원이시라면 로그인해주세요
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        push_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_up = findViewById(R.id.email_up); //이메일 입력
                password_up = findViewById(R.id.password_up); //패스워드 입력
                passwordck_up = findViewById(R.id.passwordck_up); //패스워드확인 입력

                email_full = email_up.getText().toString()+"@e-mirim.hs.kr"; //이메일 받은 것 + @e-mirim.hs.kr
                passwd_full = password_up.getText().toString();
                passwdck_full = passwordck_up.getText().toString();

                //체크하는 과정
                //이메일도 인증했는지 체크하는 과정 추가해야 함

                    if (passwd_full.matches("^[a-zA-Z0-9_*]{5,12}$")) { //비밀번호 정규표현식 검사(영 대소문자, 숫자, _, * 특수기호로 이루어진 5~12글자) - true일 때
                        if (passwd_full.equals(passwdck_full)) { //&비밀번호, 비밀번호 확인이 일치할 때 - true,true
                            be_passwd_regex = true;
                            be_passwd_equal = true;
                        } else{
                            be_passwd_regex = true;
                            be_passwd_equal = false;
                        }
                    } else {
                            be_passwd_regex = false;
                            be_passwd_equal = false;
                    }

                be_uptext = "";
                //맞을 경우 - DB에 정보 넣고, Main으로 이동
                if(be_email&&be_passwd_equal&&be_passwd_regex){
                    //DB처리 - 이메일,비밀번호 DB에 넣기
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    if(be_email == false) be_uptext = be_uptext + "'이메일 인증 안됨'  ";
                    if(be_passwd_regex == false) be_uptext = be_uptext + "'비밀번호에는 5~12 길이의 영 대소문자, 숫자, 특수기호 '_' '*'만 사용 가능'  ";
                    if(be_passwd_equal == false) be_uptext = be_uptext + "'비밀번호와 비밀번호 확인 불일치'  ";
                    }
                    be_uptext = be_uptext + "의 이유로 회원가입 하실 수 없습니다";
                    be_up.setText(be_uptext);
                }
        });

    }

    public void countDownTimer() { //카운트 다운 메소드

        time_counter = (TextView) dialogLayout.findViewById(R.id.emailAuth_time_counter);
        //줄어드는 시간을 나타내는 TextView
        emailAuth_number = (EditText) dialogLayout.findViewById(R.id.emailAuth_number);
        //사용자 인증 번호 입력창
        emailAuth_btn = (Button) dialogLayout.findViewById(R.id.emailAuth_btn);
        //인증하기 버튼


        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) { //(300초에서 1초 마다 계속 줄어듬)


            long emailAuthCount = millisUntilFinished / 1000;
                Log.d("Alex", emailAuthCount + "");

                if ((emailAuthCount - ((emailAuthCount / 60) * 60)) >= 10) { //초가 10보다 크면 그냥 출력
                time_counter.setText((emailAuthCount / 60) + " : " + (emailAuthCount - ((emailAuthCount / 60) * 60)));
            } else { //초가 10보다 작으면 앞에 '0' 붙여서 같이 출력. ex) 02,03,04...
                time_counter.setText((emailAuthCount / 60) + " : 0" + (emailAuthCount - ((emailAuthCount / 60) * 60)));
            }

            //emailAuthCount은 종료까지 남은 시간임. 1분 = 60초 되므로,
            // 분을 나타내기 위해서는 종료까지 남은 총 시간에 60을 나눠주면 그 몫이 분이 된다.
            // 분을 제외하고 남은 초를 나타내기 위해서는, (총 남은 시간 - (분*60) = 남은 초) 로 하면 된다.

        }
            @Override
            public void onFinish() { //시간이 다 되면 다이얼로그 종료
                authDialog.cancel();
            }
        }.start();

        emailAuth_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.text_email:

                dialog = LayoutInflater.from(this);
                dialogLayout = dialog.inflate(R.layout.authlayout, null); // LayoutInflater를 통해 XML에 정의된 Resource들을 View의 형태로 반환 시켜 줌
                authDialog = new Dialog(this); //Dialog 객체 생성
                authDialog.setContentView(dialogLayout); //Dialog에 inflate한 View를 탑재 하여줌
                authDialog.setCanceledOnTouchOutside(false); //Dialog 바깥 부분을 선택해도 닫히지 않게 설정함.
                authDialog.setOnCancelListener(this); //다이얼로그를 닫을 때 일어날 일을 정의하기 위해 onCancelListener 설정
                authDialog.show(); //Dialog를 나타내어 준다.
                countDownTimer();
                break;

            case R.id.emailAuth_btn : //다이얼로그 내의 인증번호 인증 버튼을 눌렀을 시

                if(emailAuth_number.getText().toString().equals("")) {
                    Toast.makeText(this, "이메일 인증 실패", Toast.LENGTH_SHORT).show();
                } else {
                    int user_answer = Integer.parseInt(emailAuth_number.getText().toString());
                    if (user_answer == randomNum) {
                        Toast.makeText(this, "이메일 인증 성공", Toast.LENGTH_SHORT).show();
                        authDialog.cancel();
                    } else {
                        Toast.makeText(this, "이메일 인증 실패", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
        }

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        countDownTimer.cancel();
    } //다이얼로그 닫을 때 카운트 다운 타이머의 cancel()메소드 호출

}
