package activity;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.MyRecycleViewAdapter_HanZiResult;
import adapter.MyRecycleViewAdapter_PinYinBuShou;
import model.BuShou;
import model.ChengYu_m;
import model.HanZi;
import model.Joke;
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
    //拼音查字时网络请求返回汉字结果集
    private List<HanZi> listhanzi=new ArrayList<HanZi>();
    private  int totalpage=1;
    private  int totalcount=0;

    private  int mposition=0;
    private MyRecycleViewAdapter_PinYinBuShou adapter1;
    private MyRecycleViewAdapter_HanZiResult adapter2;

    //网络请求
    private static OkHttpClient client=new OkHttpClient();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.hanziresult_list);

        //加载控件
        initView();

        intent=getIntent();
        tag=intent.getStringExtra("tag");
        key=intent.getStringExtra("key");
        //判断查询类型
        if(tag.equals("pinyin")){
            pinYinList1= (List<PinYin>) intent.getSerializableExtra("alllist");
            keylist= (List<String>) intent.getSerializableExtra("onekeylist");
            Log.i("查询类型为：拼音查字  查询关键字：",key);
            //获取数据
            initData(key,1,"pinyin");
        }else{
            buSouList1= (List<BuShou>) intent.getSerializableExtra("alllist");
            keylist= (List<String>) intent.getSerializableExtra("onekeylist");
            Log.i("查询类型为：部首查字  查询关键字：",key);
            //获取数据
            initData(key,1,"bushou");
        }


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

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.addItemDecoration(new DividerLinearItemDecoration(this, DividerLinearItemDecoration.VERTICAL_LIST));



    }

    private int initDataCount(final String key,final String type) {
        final int[] totalpage = {1};
        String url=" http://v.juhe.cn/xhzd/querypy?key=08f921c78a0454069f5938834efa7d4d&word="+key+"";
        if (type.equals("pinyin")){
                 url=" http://v.juhe.cn/xhzd/querypy?key=08f921c78a0454069f5938834efa7d4d&word="+key+"";
        }else if (type.equals("bushou")){

               url=" http://v.juhe.cn/xhzd/querybs?key=08f921c78a0454069f5938834efa7d4d&word="+key.substring(0,key.length()-1)+"";

        }
        Log.i("urlllllllll",url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {

            JSONObject jsonobject;
            JSONObject jsonobject2;

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String s = response.body().string();
                try {
                    jsonobject=new JSONObject(s);
                    if(jsonobject.getString("reason").equals("返回成功")){
                        jsonobject2=new  JSONObject(jsonobject.getString("result"));
                        totalpage[0] = Integer.parseInt(jsonobject2.getString("totalpage"));

                }else{
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ShowHanZiResultList.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<=totalcount/50;i++){
                            initData(key,i+1,type);
                        }
                    }
                });
            }
        });

        return totalpage[0];
    }

    private void initData(String key,int page,String type) {

        String url="";
        if (type.equals("pinyin")){
            url=" http://v.juhe.cn/xhzd/querypy?word="+key+"&dtype=&page="+page+"&pagesize=50&isjijie=&isxiangjie=&key=08f921c78a0454069f5938834efa7d4d";
        }else if (type.equals("bushou")){
            url=" http://v.juhe.cn/xhzd/querybs?word="+key.substring(0,key.length()-1)+"&dtype=&page="+page+"&pagesize=50&isjijie=&isxiangjie=&key=08f921c78a0454069f5938834efa7d4d";

        }
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {

            JSONObject jsonobject;
            JSONObject jsonobject2;
            JSONArray jsonArray;
            List<HanZi> mlist=new ArrayList<HanZi>();
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String s = response.body().string();
                try {
                    jsonobject=new JSONObject(s);
                    if(jsonobject.getString("reason").equals("返回成功")){

                    jsonobject2=new  JSONObject(jsonobject.getString("result"));

                    jsonArray=jsonobject2.getJSONArray("list");
                    for(int i=0;i<jsonArray.length();i++) {

                        HanZi hanzi=new HanZi();
                        JSONObject json= (JSONObject) jsonArray.get(i);
                        hanzi.setBushou(json.getString("bushou"));
                        hanzi.setBihua(json.getString("bihua"));
                        hanzi.setId(json.getString("id"));
                        hanzi.setPinyin(json.getString("pinyin"));
                        hanzi.setPy(json.getString("py"));
                        hanzi.setWubi(json.getString("wubi"));
                        hanzi.setZi(json.getString("zi"));
                        listhanzi.add(hanzi);
                       Log.i("汉字++++++++",hanzi.toString());
                    }}else{
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ShowHanZiResultList.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter2=new MyRecycleViewAdapter_HanZiResult(ShowHanZiResultList.this);
                        adapter2.setList(listhanzi);
                        mRecyclerView2.setAdapter(adapter2);
                    }
                });
            }
        });

    }

    private void initView() {
        back= (ImageView) findViewById(R.id.hanziresult_list_back);
        pagetitle= (TextView) findViewById(R.id.hanziresult_list_title);
        mRecyclerView1= (RecyclerView) findViewById(R.id.hanziresult_list_recyclerview1);
        mRecyclerView2= (RecyclerView) findViewById(R.id.hanziresult_list_recyclerview2);

    }
}
