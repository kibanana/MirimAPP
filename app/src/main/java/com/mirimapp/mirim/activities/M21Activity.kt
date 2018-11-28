package com.mirimapp.mirim.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

import com.dahyeon.mirim.R
import com.mirimapp.mirim.models.NoticeModel
import com.mirimapp.mirim.network.Connector
import com.mirimapp.mirim.network.Res
import com.mirimapp.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_m2_1.*

import java.util.ArrayList
import java.util.Calendar
import java.util.GregorianCalendar


class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.textview_m_2_1_recycleritem_title)
    val owner = view.findViewById<TextView>(R.id.textview_m_2_1_recycleritem_owner)
    val createdAt = view.findViewById<TextView>(R.id.textview_m_2_1_recycleritem_created_at)

    fun bind(notice: NoticeModel, context: Context) {
        title.text = notice.title
        owner.text = notice.ownerEmail
        createdAt.text = notice.createdAt
    }
}

class Adapter(val items: ArrayList<NoticeModel>, val context: Context): RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_m2_1_recycleritem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], context)
    }
}

class M21Activity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "공지사항"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m2_1)

        recyclerview_m21_list.layoutManager = LinearLayoutManager(this)

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

        button_m21_insert.setOnClickListener {
            // 2. 레이아웃 파일 edit_box.xml 을 불러와서 화면에 다이얼로그를 보여줍니다.
            val builder = AlertDialog.Builder(this@M21Activity)

            val view = LayoutInflater.from(this@M21Activity)
                .inflate(R.layout.edit_box, null, false)
            builder.setView(view)

            val ButtonSubmit = view.findViewById<View>(R.id.button_dialog_submit) as Button
            val editTextName = view.findViewById<View>(R.id.edittext_dialog_name) as EditText
            val editTextSub_Writer = view.findViewById<View>(R.id.edittext_dialog_sub_writer) as EditText
            val editTextSub_Content = view.findViewById<View>(R.id.edittext_dialog_sub_content) as EditText

            ButtonSubmit.text = "추가"

            val dialog = builder.create()

            // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면
            ButtonSubmit.setOnClickListener {
                // 4. 사용자가 입력한 내용을 가져와서
                val strName = editTextName.text.toString()
                val strSub_writer = editTextSub_Writer.text.toString()
                val cal = GregorianCalendar()
                val y = cal.get(Calendar.YEAR)
                val m = cal.get(Calendar.MONTH) + 1 //+1 해야 올바른값
                val d = cal.get(Calendar.DAY_OF_MONTH)
                val strSub_Date = y.toString() + "." + m + "." + d
                val strSub_Content = editTextSub_Content.text.toString()

                dialog.dismiss()
            }

            dialog.show()
        }

        Connector.api.getNoticeList(getToken()).enqueue(
            object: Res<ArrayList<NoticeModel>>(this) {
                override fun callBack(code: Int, body: ArrayList<NoticeModel>?) {
                    if (code == 200) {
                        recyclerview_m21_list.adapter = Adapter(body!!, context)
                    }
                }
            }
        )
    }
}
