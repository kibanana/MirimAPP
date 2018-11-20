package com.mirimapp.mirim.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dahyeon.mirim.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>  {

    private ArrayList<Dictionary> mList;
    private Context mContext;

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener { // 1. 리스너 추가

        protected TextView mName;
        protected TextView mSub_Writer;
        protected TextView mSub_Date;
        protected TextView mSub_hit; //여기에 조회수 표시해줘야 함

        public CustomViewHolder(View view) {
            super(view);

            this.mName = (TextView) view.findViewById(R.id.tv_itemName);
            this.mSub_Writer = (TextView) view.findViewById(R.id.tv_itemSub_Writer);
            this.mSub_Date = (TextView) view.findViewById(R.id.tv_itemSub_Date);
            this.mSub_hit = (TextView) view.findViewById(R.id.tv_hit);

            view.setOnCreateContextMenuListener(this); //2. 리스너 등록
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 메뉴 추가U

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            MenuItem View = menu.add(Menu.NONE, 1003, 3, "상세 보기");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
            View.setOnMenuItemClickListener(onEditMenu);
        }

        // 4. 캔텍스트 메뉴 클릭시 동작을 설정
                        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch (item.getItemId()) {
                                    case 1001:
                                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextName = (EditText) view.findViewById(R.id.edittext_dialog_name);
                        final EditText editTextSub_Writer = (EditText) view.findViewById(R.id.edittext_dialog_sub_writer);
                        final EditText editTextSub_Content = (EditText) view.findViewById(R.id.edittext_dialog_sub_content);

                        editTextName.setText(mList.get(getAdapterPosition()).getName());
                        editTextSub_Writer.setText(mList.get(getAdapterPosition()).getSub_writer());
                        editTextSub_Content.setText(mList.get(getAdapterPosition()).getSub_content());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                String strName = editTextName.getText().toString();
                                String strSub_Writer = editTextSub_Writer.getText().toString();
                                Calendar cal = new GregorianCalendar();
                                int y = cal.get(Calendar.YEAR);
                                int m = cal.get(Calendar.MONTH)+1; //+1 해야 올바른값
                                int d = cal.get(Calendar.DAY_OF_MONTH);
                                String strSub_Date = y+"."+m+"."+d;
                                String strSub_Content = editTextSub_Content.getText().toString();

                                Dictionary dict = new Dictionary(strName, strSub_Writer, strSub_Date, strSub_Content);

                                mList.set(getAdapterPosition(), dict);
                                notifyItemChanged(getAdapterPosition());

                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;

                    case 1002:
                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());
                        break;

                        case 1003:
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(mContext);
                            view = LayoutInflater.from(mContext)
                                    .inflate(R.layout.view_box, null, false);
                            builder2.setView(view);

                            final Button ButtonCheck = (Button) view.findViewById(R.id.btn_dialog_ck);
                            final TextView tv_name = (TextView) view.findViewById(R.id.view_tv_name);
                            final TextView tv_writer = (TextView) view.findViewById(R.id.view_tv_writer);
                            final TextView tv_date = (TextView) view.findViewById(R.id.view_tv_date);
                            final TextView tv_hit = (TextView) view.findViewById(R.id.view_tv_hit);
                            final TextView tv_content = (TextView)  view.findViewById(R.id.view_tv_content);

                            tv_name.setText((mList).get(getAdapterPosition()).getName());
                            tv_writer.setText((mList).get(getAdapterPosition()).getSub_writer());
                            tv_date.setText((mList).get(getAdapterPosition()).getSub_date());
                            tv_hit.setText("5"); // 조회수는 DB에서 얻어와야 함
                            tv_content.setText((mList).get(getAdapterPosition()).getSub_content());

                            notifyItemChanged(getAdapterPosition());


                            final AlertDialog dialog2 = builder2.create();
                            ButtonCheck.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {

                                    dialog2.dismiss();
                                }
                            });
                            dialog2.show();
                            break;
                }
                return true;
            }
        };
    }

    public RecyclerAdapter(Context context, ArrayList<Dictionary> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.mName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mSub_Writer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mSub_Date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.mName.setGravity(Gravity.CENTER);
        viewholder.mSub_Writer.setGravity(Gravity.CENTER);
        viewholder.mSub_Date.setGravity(Gravity.CENTER);

        viewholder.mName.setText(mList.get(position).getName());
        viewholder.mSub_Writer.setText(mList.get(position).getSub_writer());
        viewholder.mSub_Date.setText(mList.get(position).getSub_date());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}