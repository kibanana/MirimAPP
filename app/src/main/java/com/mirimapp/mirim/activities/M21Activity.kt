package com.mirimapp.mirim.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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
import kotlinx.android.synthetic.main.edit_box.*

import java.util.ArrayList


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
    fun loadNotices() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "공지사항"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m2_1)

        recyclerview_m21_list.layoutManager = LinearLayoutManager(this)
        loadNotices()

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
            val builder = AlertDialog.Builder(this@M21Activity)
            val view = LayoutInflater.from(this@M21Activity)
                .inflate(R.layout.edit_box, null, false)
            builder.setView(view)

            val dialog = builder.create()

            dialog.show()
            dialog.setOnDismissListener {
                loadNotices()
            }

            dialog.button_dialog_submit.setOnClickListener {
                // 4. 사용자가 입력한 내용을 가져와서
                Connector.api.addNotice(getToken(), hashMapOf(
                    "title" to dialog.edittext_dialog_title.text.toString(),
                    "content" to dialog.edittext_dialog_content.text.toString()
                )).enqueue(
                    object: Res<Void>(this) {
                        override fun callBack(code: Int, body: Void?) {
                            if (code == 201) {
                                showToast("게시글 추가 완료")
                                dialog.dismiss()
                            } else {
                                showToast("문제가 발생했습니다.")
                            }
                        }
                    }
                )
            }
        }
    }
}
