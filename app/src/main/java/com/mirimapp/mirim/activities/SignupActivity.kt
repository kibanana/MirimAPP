package com.mirimapp.mirim.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.dahyeon.mirim.R
import com.mirimapp.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.dialog_email_certify.*

class SignupActivity : BaseActivity() {
    val passwordRegex = "^[a-zA-Z0-9_*]{5,12}$".toRegex()
    //비밀번호는 5~12글자 이하, _, * 특수문자만 사용 가능

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        textview_signup_go_to_signin.setOnClickListener {
            startActivity(Intent(applicationContext, SigninActivity::class.java))
        }

        button_signup_confirm.setOnClickListener {
            val email = edittext_signup_email.text.toString() + "@e-mirim.hs.kr" //이메일 받은 것 + @e-mirim.hs.kr
            val password = edittext_signup_password.text.toString()
            val passwordToCheck = edittext_signup_password_check.text.toString()

            if(email.isEmpty() || password.isEmpty() || passwordToCheck.isEmpty()) {
                // TODO
            }

            if (password.matches(passwordRegex)) {
                if (password == passwordToCheck) {
                    // TODO signup 호출
                } else {
                    textview_signup_invalid_reason.setText("비밀번호와 비밀번호 확인 불일치")
                }
            } else {
                textview_signup_invalid_reason.setText("비밀번호에는 5~12 길이의 영 대소문자, 숫자, 특수기호 '_' '*'만 사용 가능")
            }
        }

        textview_signup_send_certify_mail.setOnClickListener {
            // TODO email duplicate check 호출

            val dialog = LayoutInflater.from(this)
            val dialogLayout = dialog.inflate(
                R.layout.dialog_email_certify,
                null
            )
            val authDialog = Dialog(this)
            authDialog.setContentView(dialogLayout)
            authDialog.show()
        }
    }

    fun registerDialog() {
        button_certify_confirm.setOnClickListener {
            val certifyCode = edittext_certify_code.text.toString()

            // TODO email certify 호출
        }
    }
}
