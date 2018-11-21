package com.mirimapp.mirim.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import com.dahyeon.mirim.R
import com.mirimapp.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menu1 = findViewById<ImageButton>(R.id.menu_btn1)
        val menu2 = findViewById<ImageButton>(R.id.menu_btn2)
        val menu3 = findViewById<ImageButton>(R.id.menu_btn3)
        val menu4 = findViewById<ImageButton>(R.id.menu_btn4)
        val menu5 = findViewById<ImageButton>(R.id.menu_btn5)

        menu1.setOnClickListener {
            val intent = Intent(applicationContext, Menu1SchoolActivity::class.java)
            startActivity(intent)
        }

        menu2.setOnClickListener {
            val intent = Intent(applicationContext, Menu2DomitoryActivity::class.java)
            startActivity(intent)
        }

        menu3.setOnClickListener {
            val intent = Intent(applicationContext, Menu3BoardActivity::class.java)
            startActivity(intent)
        }

        menu4.setOnClickListener {
            val intent = Intent(applicationContext, Menu4MarketActivity::class.java)
            startActivity(intent)
        }

        menu5.setOnClickListener {
            val intent = Intent(applicationContext, Menu5MypageActivity::class.java)
            startActivity(intent)
        }

        button_main_logout.setOnClickListener {
            removeToken(true)
            removeToken(false)
            startActivity(Intent(applicationContext, SigninActivity::class.java))
            finish()
        }
    }
}
