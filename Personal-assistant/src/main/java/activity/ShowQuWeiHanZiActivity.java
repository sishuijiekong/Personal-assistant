package activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.MyRecycleViewAdapter_HanZiResult_Item;
import adapter.MyRecycleViewAdapter_QuWeiHanZi;
import model.HanZi;
import model.QuWeiHanZi;
import mysqlite.MYDB;
import util.DividerGridItemDecoration;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/7.
 * 趣味汉字具体某个类型汉字列表显示
 */
public class ShowQuWeiHanZiActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView title;
    private ImageView back;
    private RecyclerView mRecyclerView;
    private Intent intent;
    private String type;
    private String type2;
    private MyRecycleViewAdapter_QuWeiHanZi adapter;
    private List<QuWeiHanZi> list=new ArrayList<>();
    private MYDB mMYDB=new MYDB(this);
    private Cursor mCursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        intent=getIntent();
        type=intent.getStringExtra("type");
        type2=intent.getStringExtra("type");

        mCursor=mMYDB.quweihanziselect(type);


        if(type.equals("0")){
            type="笔画最多的汉字";
        }else if(type.equals("1")){
            type="左右相同的汉字";
        }
        else if(type.equals("2")){
            type="上下相同的汉字";
        }
        else if(type.equals("3")){
            type="三个相同字组成的汉字";
        }
        else if(type.equals("4")){
            type="四个相同字组成的汉字";
        }
        setContentView(R.layout.showquweihanzi);

        title= (TextView) findViewById(R.id.showquweihanzi_title);
        title.setText(type);

        back=(ImageView) findViewById(R.id.showquweihanzi_back);
        back.setOnClickListener(this);



        while (mCursor.moveToNext()){
            QuWeiHanZi quweihanzi=new QuWeiHanZi();
            quweihanzi.setBihua(mCursor.getString(4));
            quweihanzi.setPinyin(mCursor.getString(2));
            quweihanzi.setType(mCursor.getString(3));
            quweihanzi.setZi(mCursor.getString(1));
            list.add(quweihanzi);
        }
        mRecyclerView= (RecyclerView) findViewById(R.id.showquweihanzi_recyclerview);
        adapter=new MyRecycleViewAdapter_QuWeiHanZi (this,list,type2);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,5));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        adapter.setOnItemClickLitener(new MyRecycleViewAdapter_QuWeiHanZi.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(ShowQuWeiHanZiActivity.this,ShowHanZiActivity.class);
                intent.putExtra("name",list.get(position).getZi());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
