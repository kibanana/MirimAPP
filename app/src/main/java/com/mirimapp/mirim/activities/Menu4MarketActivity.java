package com.mirimapp.mirim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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

public class Menu4MarketActivity extends AppCompatActivity {

    // 일단 뭐라도 나타나야 하니까 name이랑 sub를 String 배열을 이용하여 만들어 보았음.
    private String[] names = {"[공구] 1", "[판매] 2", "[판매] 3", "[판매] 4", "[공구] 5", "[공구] 6", "[판매] 7", "[공구] 8", "[공구] 9"};
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
        setTitle("중고장터");
        setContentView(R.layout.activity_menu4_market);

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
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_in);
            }
        });

        menu_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu4MarketActivity.class);
                startActivity(intent);
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
        /*
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_m4_list);
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

        Button buttonInsert = (Button)findViewById(R.id.button_m4_insert);
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

        //이거(2) 가 리사이클러뷰 관련
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_m4_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mArrayList = new ArrayList<>();

        mAdapter = new RecyclerAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter);
        Button buttonInsert = (Button)findViewById(R.id.button_m4_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {

            // 1. 화면 아래쪽에 있는 데이터 추가 버튼을 클릭하면
            @Override
            public void onClick(View v) {
                // 2. 레이아웃 파일 edit_box.xml 을 불러와서 화면에 다이얼로그를 보여줍니다.
                AlertDialog.Builder builder = new AlertDialog.Builder(Menu4MarketActivity.this);

                View view = LayoutInflater.from(Menu4MarketActivity.this)
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
