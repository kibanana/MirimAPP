package com.mirimapp.mirim.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

import com.dahyeon.mirim.R
import com.mirimapp.mirim.models.PostModel
import com.mirimapp.mirim.network.Connector
import com.mirimapp.mirim.network.Res
import com.mirimapp.mirim.util.BaseActivity
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_menu3_board.*
import kotlinx.android.synthetic.main.edit_box.*

import java.util.ArrayList

class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.textview_menu3_board_recycleritem_title)
    val createdAt = view.findViewById<TextView>(R.id.textview_menu3_board_recycleritem_created_at)

    fun bind(post: PostModel, context: Context) {
        title.text = post.title
        createdAt.text = post.createdAt
    }
}

class PostAdapter(val items: ArrayList<PostModel>, val context: Context): RecyclerView.Adapter<PostViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_menu3_board_recycleritem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position], context)
    }
}

class Menu3BoardActivity : BaseActivity() {
    fun loadPosts() {
        Connector.api.getPostList(getToken()).enqueue(
            object: Res<ArrayList<PostModel>>(this) {
                override fun callBack(code: Int, body: ArrayList<PostModel>?) {
                    if (code == 200) {
                        recyclerview_m3_list.adapter = PostAdapter(body!!, context)
                    }
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "익명 게시판"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu3_board)

        recyclerview_m3_list.layoutManager = LinearLayoutManager(this)
        loadPosts()

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

        button_m3_insert.setOnClickListener {
            val builder = AlertDialog.Builder(this@Menu3BoardActivity)
            val view = LayoutInflater.from(this@Menu3BoardActivity)
                .inflate(R.layout.edit_box, null, false)
            builder.setView(view)

            val dialog = builder.create()

            dialog.show()
            dialog.setOnDismissListener {
                loadPosts()
            }

            dialog.button_dialog_submit.setOnClickListener {
                // 4. 사용자가 입력한 내용을 가져와서
                Connector.api.addPost(getToken(), hashMapOf(
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
