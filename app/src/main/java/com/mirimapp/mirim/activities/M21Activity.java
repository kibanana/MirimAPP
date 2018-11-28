package com.mirimapp.mirim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dahyeon.mirim.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class M21Activity extends AppCompatActivity {

    // 일단 뭐라도 나타나야 하니까 name이랑 sub를 String 배열을 이용하여 만들어 보았음.
    private String[] names = {"[규칙] 1", "[점호] 2", "[규칙] 3", "[규칙] 4", "[점검] 5", "[점검] 6", "[시간 변동] 7", "[점검] 8", "[시간 변동] 9"};
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

    //이거 (1)
    private static String TAG = "recyclerview_example";

    private ArrayList<Dictionary> mArrayList;
    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2_1);
        setTitle("공지사항");

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


        Button menu_btn2 = findViewById(R.id.button_drawer_to_dormitory_activity);
        Button menu_btn3 = findViewById(R.id.button_drawer_to_board_activity);
        Button menu_btn4 = findViewById(R.id.button_drawer_to_market_activity);
        Button menu_btn5 = findViewById(R.id.button_drawer_to_mypage_activity);

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
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
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
            }
        });
//이거(2) 가 리사이클러뷰 관련
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_m21_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mArrayList = new ArrayList<>();

        mAdapter = new RecyclerAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);
        Button buttonInsert = (Button)findViewById(R.id.button_m21_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {

            // 1. 화면 아래쪽에 있는 데이터 추가 버튼을 클릭하면
            @Override
            public void onClick(View v) {
                // 2. 레이아웃 파일 edit_box.xml 을 불러와서 화면에 다이얼로그를 보여줍니다.
                AlertDialog.Builder builder = new AlertDialog.Builder(M21Activity.this);

                View view = LayoutInflater.from(M21Activity.this)
                        .inflate(R.layout.edit_box, null, false);
                builder.setView(view);

                final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                final EditText editTextName = (EditText) view.findViewById(R.id.edittext_dialog_name);
                final EditText editTextSub_Writer = (EditText) view.findViewById(R.id.edittext_dialog_sub_writer);
                final EditText editTextSub_Content = (EditText) view.findViewById(R.id.edittext_dialog_sub_content);

                ButtonSubmit.setText("추가");

                final AlertDialog dialog = builder.create();

                // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면
                ButtonSubmit.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        // 4. 사용자가 입력한 내용을 가져와서
                        String strName = editTextName.getText().toString();
                        String strSub_writer = editTextSub_Writer.getText().toString();
                        Calendar cal = new GregorianCalendar();
                        int y = cal.get(Calendar.YEAR);
                        int m = cal.get(Calendar.MONTH)+1; //+1 해야 올바른값
                        int d = cal.get(Calendar.DAY_OF_MONTH);
                        String strSub_Date = y+"."+m+"."+d;
                        String strSub_Content = editTextSub_Content.getText().toString();

                        // 5. ArrayList에 추가하고
                        Dictionary dict = new Dictionary(strName, strSub_writer, strSub_Date, strSub_Content);
                        mArrayList.add(0, dict); //첫번째 줄에 삽입됨
                        //mArrayList.add(dict); //마지막 줄에 삽입됨

                        // 6. 어댑터에서 RecyclerView에 반영하도록 합니다.
                        mAdapter.notifyItemInserted(0);
                        //mAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
