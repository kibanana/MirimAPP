package com.mirimapp.mirim.activities

import android.content.Intent
import android.os.Bundle
import com.dahyeon.mirim.R
import com.mirimapp.mirim.models.AuthModel
import com.mirimapp.mirim.network.Connector
import com.mirimapp.mirim.network.Res
import com.mirimapp.mirim.util.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val refreshToken = getToken(false)

        if (refreshToken.isEmpty()) {
            startActivity(Intent(applicationContext, SigninActivity::class.java))
            finish()
        } else {
            Connector.api.refresh(
                refreshToken
            ).enqueue(object : Res<AuthModel>(this) {
                override fun callBack(code: Int, body: AuthModel?) {
                    when(code) {
                        200 -> {
                            saveToken(body!!.token)
                            null
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
                            startActivity(Intent(applicationContext, SigninActivity::class.java))
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
