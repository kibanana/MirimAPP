package com.mirimapp.mirim.activities

import android.content.Intent
import android.os.Bundle
import com.dahyeon.mirim.R
import com.mirimapp.mirim.models.AuthModel
import com.mirimapp.mirim.network.Connector
import com.mirimapp.mirim.network.Res
import com.mirimapp.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        button_signin_login.setOnClickListener {
            val emailPrefix = edittext_signin_email.text.toString()
            val password = edittext_signin_password.text.toString()

            if (emailPrefix.isEmpty() || password.isEmpty()) {
                showToast("값을 모두 입력하세요.")
            } else {
                val emailWithSuffix = emailPrefix + getString(R.string.email_suffix)

                Connector.api.auth(
                    hashMapOf(
                        "email" to emailWithSuffix,
                        "pw" to password
                    )
                ).enqueue(object: Res<AuthModel>(this) {
                    override fun callBack(code: Int, body: AuthModel?) {
                        when(code) {
                            200 -> {
                                saveToken(body!!.token)
                                saveToken(body.refreshToken!!, false)
                                null
                            }
                            401 -> {
                                "이메일 또는 비밀번호를 확인하세요."
                            }
                            else -> {
                                "오류: $code"
                            }
                        }.let {
                            if(it != null) {
                                showToast(it)
                            } else {
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                                finish()
                            }
                        }
                    }
                })
            }
        }

        textview_signin_go_to_signup.setOnClickListener {
            startActivity(Intent(applicationContext, SignupActivity::class.java))
        }
    }
}
