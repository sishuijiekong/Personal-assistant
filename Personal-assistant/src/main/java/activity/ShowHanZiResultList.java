package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.MyRecycleViewAdapter_HanZiResult;
import adapter.MyRecycleViewAdapter_PinYinBuShou;
import model.BuShou;
import model.ChengYu_m;
import model.PinYin;
import util.DividerLinearItemDecoration;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/5.
 * 汉字搜索结果列表显示页面
 */
public class ShowHanZiResultList extends AppCompatActivity{

    //接收上一个页面传送的数据
    private Intent intent;
    //所有拼音列表
    private List<PinYin> pinYinList1=new ArrayList<>();
    //所有部首列表
    private List<BuShou> buSouList1=new ArrayList<>();
    //查询关键字列表
    private List<String> keylist=new ArrayList<>();
    //查询关键字
    private  String key;
    //通过tag 判断查询情况 拼音查字：tag=pinyin 部首查字：tag=bushou
    private  String tag;

    //返回按钮
    private ImageView back;
    //页面标题
    private TextView pagetitle;
    //查询菜单列表
    private RecyclerView mRecyclerView1;
    //查询结果列表
    private RecyclerView mRecyclerView2;

    private MyRecycleViewAdapter_PinYinBuShou adapter1;
    private MyRecycleViewAdapter_HanZiResult adapter2;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.hanziresult_list);

        intent=getIntent();
        tag=intent.getStringExtra("tag");
        key=intent.getStringExtra("key");
        //判断查询类型
        if(tag.equals("pinyin")){
            pinYinList1= (List<PinYin>) intent.getSerializableExtra("alllist");
            keylist= (List<String>) intent.getSerializableExtra("onekeylist");
            Log.i("查询类型为：拼音查字  查询关键字：",key);
        }else{
            buSouList1= (List<BuShou>) intent.getSerializableExtra("alllist");
            keylist= (List<String>) intent.getSerializableExtra("onekeylist");
            Log.i("查询类型为：部首查字  查询关键字：",key);
        }

        //加载控件
        initView();

        //获取数据
        initData();

        //给控件加载数据
        loadData();
        //事件监听
        initEvent();
    }

    private void initEvent() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadData() {
        pagetitle.setText(key);
        for (int i=0;i<keylist.size();i++){
            Log.i("keylist",keylist.get(i).toString());
        }
        adapter1=new MyRecycleViewAdapter_PinYinBuShou(this,keylist);
        mRecyclerView1.setAdapter(adapter1);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView1.addItemDecoration(new DividerLinearItemDecoration(this, DividerLinearItemDecoration.VERTICAL_LIST));
        adapter1.setOnItemClickLitener(new MyRecycleViewAdapter_PinYinBuShou.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView textview= (TextView) view.findViewById(R.id.hanziresult_list_title);
                pagetitle.setText(keylist.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


        List<ChengYu_m>  list2=new ArrayList<>();
            for(int i=0;i<10;i++){
                list2.add(new ChengYu_m());
            }
        adapter2=new MyRecycleViewAdapter_HanZiResult(this,list2);
        mRecyclerView2.setAdapter(adapter2);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.addItemDecoration(new DividerLinearItemDecoration(this, DividerLinearItemDecoration.VERTICAL_LIST));
        adapter1.setOnItemClickLitener(new MyRecycleViewAdapter_PinYinBuShou.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView textview= (TextView) view.findViewById(R.id.hanziresult_list_title);
                pagetitle.setText(keylist.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


    }

    private void initData() {

    }

    private void initView() {
        back= (ImageView) findViewById(R.id.hanziresult_list_back);
        pagetitle= (TextView) findViewById(R.id.hanziresult_list_title);
        mRecyclerView1= (RecyclerView) findViewById(R.id.hanziresult_list_recyclerview1);
        mRecyclerView2= (RecyclerView) findViewById(R.id.hanziresult_list_recyclerview2);

    }
}
