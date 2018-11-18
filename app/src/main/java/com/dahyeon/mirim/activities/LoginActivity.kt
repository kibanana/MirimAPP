package com.dahyeon.mirim.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.dahyeon.mirim.R
import com.dahyeon.mirim.models.AuthModel
import com.dahyeon.mirim.network.Connector
import com.dahyeon.mirim.network.Res
import com.dahyeon.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_main_login.setOnClickListener {
            val emailPrefix = edittext_main_email.text
            val password = edittext_main_password.text

            if (emailPrefix.isEmpty() || password.isEmpty()) {
                showToast("값을 모두 입력하세요.")
            } else {
                Log.d("@@@@@", "before auth")
                Log.d("@@@@@", hashMapOf(
                    "email" to "$emailPrefix@e-mirim.hs.kr",
                    "pw" to password
                ).toString())

                Connector.api.auth(
                    hashMapOf(
                        "email" to "$emailPrefix@e-mirim.hs.kr",
                        "pw" to password
                    )
                ).enqueue(object: Res<AuthModel>(this) {
                    override fun callBack(code: Int, body: AuthModel?) {
                        Log.d("@@@@@", "callback")
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
                            }
                        }
                    }
                })
            }
        }
    }
}
