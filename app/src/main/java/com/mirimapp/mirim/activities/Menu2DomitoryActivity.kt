package com.mirimapp.mirim.activities

import android.content.Intent
import android.support.v4.widget.DrawerLayout
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import com.dahyeon.mirim.R
import com.mirimapp.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_menu2_domitory.*

class Menu2DomitoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "기숙사"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu2_domitory)

        val buttonOpen = findViewById<View>(R.id.menu) as ImageButton
        buttonOpen.setOnClickListener {
            val drawer = findViewById<View>(R.id.drawer) as DrawerLayout
            if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
                drawer.openDrawer(Gravity.RIGHT)
            } else if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                drawer.closeDrawer(Gravity.RIGHT)
            }
        }

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

        drawer_btn_out.setOnClickListener {
            removeToken(true)
            removeToken(false)
            startActivity(Intent(applicationContext, SigninActivity::class.java))
            finish()
        }

        m2_1.setOnClickListener {
            val intent = Intent(applicationContext, M21Activity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in)
        }

        m2_2.setOnClickListener {
            val intent = Intent(applicationContext, M22Activity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in)
        }
    }
}
