package com.mirimapp.mirim.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dahyeon.mirim.R
import com.mirimapp.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    val buttonIdActivityMapping = hashMapOf(
        R.id.imagebutton_main_to_board_activity to Menu3BoardActivity::class.java,
        R.id.imagebutton_main_to_dormitory_activity to Menu2DomitoryActivity::class.java,
        R.id.imagebutton_main_to_market_activity to Menu4MarketActivity::class.java,
        R.id.imagebutton_main_to_mypage_activity to Menu5MypageActivity::class.java,
        R.id.imagebutton_main_to_school_activity to Menu1SchoolActivity::class.java
    )

    override fun onClick(v: View?) {
        val viewID = v!!.id

        if(buttonIdActivityMapping.containsKey(viewID)) {
            startActivity(Intent(applicationContext, buttonIdActivityMapping[viewID]))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_main_logout.setOnClickListener {
            removeToken(true)
            removeToken(false)
            startActivity(Intent(applicationContext, SigninActivity::class.java))
            finish()
        }
    }
}
