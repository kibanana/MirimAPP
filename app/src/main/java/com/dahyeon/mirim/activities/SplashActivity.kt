package com.dahyeon.mirim.activities

import android.content.Intent
import android.os.Bundle
import com.dahyeon.mirim.R
import com.dahyeon.mirim.models.AuthModel
import com.dahyeon.mirim.network.Connector
import com.dahyeon.mirim.network.Res
import com.dahyeon.mirim.util.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val refreshToken = getToken(false)

        if (refreshToken.isEmpty()) {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        } else {
            Connector.api.refresh(
                refreshToken
            ).enqueue(object : Res<AuthModel>(this) {
                override fun callBack(code: Int, body: AuthModel?) {
                    when(code) {
                        200 -> {
                            saveToken(body!!.token); null
                        }
                        401 -> {
                            "다시 로그인 하세요"
                        }
                        403 -> {
                            "다시 로그인 하세요"
                        }
                        else -> {
                            "오류: $code"
                        }
                    }.let {
                        if(it != null) {
                            showToast(it)
                            startActivity(Intent(applicationContext, LoginActivity::class.java))
                            finish()
                        } else {
                            val accessToken = getToken()

                            if (accessToken.isNotEmpty()) {
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                                finish()
                            }
                        }
                    }
                }
            })
        }
    }
}
