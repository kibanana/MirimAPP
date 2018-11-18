package com.mirimapp.mirim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.dahyeon.mirim.R;

import java.util.ArrayList;

public class Menu3BoardActivity extends AppCompatActivity {

    // 일단 뭐라도 나타나야 하니까 name이랑 sub를 String 배열을 이용하여 만들어 보았음.
    private String[] names = {"[잡담] 1", "[개발과] 2", "[개발과] 3", "[잡담] 4", "[잡담] 5", "[디자인과] 6", "[개발과] 7", "[개발과] 8", "[개발과] 9"};
    private String[] writers = {"Charlie","Andrew","Han","Liz","Thomas","Sky","Andy","Lee","Park"};
    private String[] hits = {"Charlie","Andrew","Han","Liz","Thomas","Sky","Andy","Lee","Park"};
    private String[] dates = {"2001/05/25", "2001/05/25", "2001/05/25", "2001/05/25", "2001/05/25", "2001/05/25", "2001/05/25",
            "2001/05/25", "2001/05/25"};

    private String[] subs = {writers[0]+" | "+hits[0]+" | "+dates[0],
            writers[1]+" | "+hits[1]+" | "+dates[1],
            writers[2]+" | "+hits[2]+" | "+dates[2],
            writers[3]+" | "+hits[3]+" | "+dates[3],
            writers[4]+" | "+hits[4]+" | "+dates[4],
            writers[5]+" | "+hits[5]+" | "+dates[5],
            writers[6]+" | "+hits[6]+" | "+dates[6],
            writers[7]+" | "+hits[7]+" | "+dates[7],
            writers[8]+" | "+hits[8]+" | "+dates[8],
    };
    //여기까지가 데이터

   /* private static String TAG = "recyclerview_example";

    private ArrayList<Dictionary> mArrayList;
    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private int count = -1;
    */ //포스팅 1에서 사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("익명 게시판");
        setContentView(R.layout.activity_menu3_board);

        ImageButton buttonOpen = (ImageButton) findViewById(R.id.menu) ;
        buttonOpen.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer) ;
                if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.openDrawer(Gravity.RIGHT) ;
                }
                else if(drawer.isDrawerOpen(Gravity.RIGHT)){
                    drawer.closeDrawer(Gravity.RIGHT);
                }
            }
        });

        Button menu_btn1 = findViewById(R.id.menu1);
        Button menu_btn2 = findViewById(R.id.menu2);
        Button menu_btn3 = findViewById(R.id.menu3);
        Button menu_btn4 = findViewById(R.id.menu4);
        Button menu_btn5 = findViewById(R.id.menu5);

        menu_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu1SchoolActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        menu_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu2DomitoryActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        menu_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu3BoardActivity.class);
                startActivity(intent);
            }
        });

        menu_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu4MarketActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        menu_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu5MypageActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        /*mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_m3_list);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        // MainActivity에서 RecyclerView의 데이터에 접근시 사용됩니다.
        mArrayList = new ArrayList<>();

        // RecyclerView를 위해 CustomAdapter를 사용합니다.
        mAdapter = new RecyclerAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        // RecyclerView의 줄(row) 사이에 수평선을 넣기위해 사용됩니다.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Button buttonInsert = (Button)findViewById(R.id.button_m3_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                // Dictionary 생성자를 사용하여 ArrayList에 삽입할 데이터를 만듭니다.
                // 임의로 만든 데이터 띄움
                String n;
                String sub;
                n = names[count];
                sub = subs[count];
                Dictionary dict = new Dictionary(n, sub);

                //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
                mArrayList.add(dict); // RecyclerView의 마지막 줄에 삽입

                mAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영
            }
        });*/ // //포스팅 1에서 사용
    }
}
