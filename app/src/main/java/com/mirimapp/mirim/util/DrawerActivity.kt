package com.mirimapp.mirim.util

import android.content.Intent
import android.os.Bundle
import com.dahyeon.mirim.R
import com.mirimapp.mirim.activities.Menu2DomitoryActivity
import com.mirimapp.mirim.activities.Menu3BoardActivity
import com.mirimapp.mirim.activities.Menu4MarketActivity
import com.mirimapp.mirim.activities.Menu5MypageActivity
import kotlinx.android.synthetic.main.activity_drawer.*

open class DrawerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        button_drawer_to_dormitory_activity.setOnClickListener {
            val intent = Intent(applicationContext, Menu2DomitoryActivity::class.java)
            startActivity(intent)
        }

        button_drawer_to_board_activity.setOnClickListener {
            val intent = Intent(applicationContext, Menu3BoardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in)
        }

        button_drawer_to_market_activity.setOnClickListener {
            val intent = Intent(applicationContext, Menu4MarketActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in)
        }

        button_drawer_to_mypage_activity.setOnClickListener {
            val intent = Intent(applicationContext, Menu5MypageActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in)
        }
    }
}
