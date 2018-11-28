package com.mirimapp.mirim.activities

import android.content.Intent
import android.os.Bundle
import com.dahyeon.mirim.R
import com.mirimapp.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_main_logout.setOnClickListener {
            removeToken(true)
            removeToken(false)
            startActivity(Intent(applicationContext, SigninActivity::class.java))
            finish()
        }

        imagebutton_main_to_board_activity.setOnClickListener {
            startActivity(Intent(applicationContext, Menu3BoardActivity::class.java))
        }
        imagebutton_main_to_dormitory_activity.setOnClickListener {
            startActivity(Intent(applicationContext, Menu2DomitoryActivity::class.java))
        }
        imagebutton_main_to_market_activity.setOnClickListener {
            startActivity(Intent(applicationContext, Menu4MarketActivity::class.java))
        }
        imagebutton_main_to_mypage_activity.setOnClickListener {
            startActivity(Intent(applicationContext, Menu5MypageActivity::class.java))
        }
    }
}
