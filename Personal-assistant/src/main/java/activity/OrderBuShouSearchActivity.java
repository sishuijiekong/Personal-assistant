package activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.MyRecycleViewAdapter_PinYinBuShou;
import model.BuShou;
import model.PinYin;
import util.DividerGridItemDecoration;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/5.
 * 按部首查字 部首列表
 */
public class OrderBuShouSearchActivity extends AppCompatActivity{

    //接收从上一个页面传过来的数据
    private Intent intent;
    private String title;

    //页面标题
    private TextView pagetitle;
    //返回按钮
    private ImageView back;
    //部首笔画列表
    private RecyclerView mRecyclerView1;
    //某个笔画数的部首列表
    private TextView mRecyclerView2_title;
    private RecyclerView mRecyclerView2;

    //部首笔画列表数据
    private List<String> bihua_list=new ArrayList<>();
    //某个笔画数的部首列表数据
    private List<String>  onekey_list=new ArrayList<>();
    //所有部首列表数据
    private List<BuShou>  all_list=new ArrayList<BuShou>();

    private MyRecycleViewAdapter_PinYinBuShou adapter1;
    private MyRecycleViewAdapter_PinYinBuShou adapter2;

    //网络请求实例
    private OkHttpClient mclient=new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        //获取页面标题
        intent=getIntent();
        title=intent.getStringExtra("title");

        //加载布局
        setContentView(R.layout.pinyin_and_bushou);


        //获取控件
        initView();
        //获取数据
        initData();
        //给控件加载数据
        loadData();

        //设置页面标题
        pagetitle.setText(title);

        adapter1=new MyRecycleViewAdapter_PinYinBuShou(this,bihua_list);
        adapter2=new MyRecycleViewAdapter_PinYinBuShou(this,onekey_list);

        mRecyclerView1.setAdapter(adapter1);
        mRecyclerView2.setAdapter(adapter2);

        //设置布局管理器 网格布局 每行五列
        mRecyclerView1.setLayoutManager(new GridLayoutManager(this,5));
        mRecyclerView1.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView2.setLayoutManager(new GridLayoutManager(this,5));
        mRecyclerView2.addItemDecoration(new DividerGridItemDecoration(this));

        //设置列表点击事件
        adapter1.setOnItemClickLitener(new MyRecycleViewAdapter_PinYinBuShou.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //清空数据列表
                onekey_list.clear();
                //获取点击控件
                TextView textview= (TextView) view.findViewById(R.id.item_pinyinbushou_view_name);
                //给数据列表加载相应的数据
                for(int i=0;i<all_list.size();i++){
                    if(textview.getText().toString().equals(all_list.get(i).getBihua())){
                        onekey_list.add(all_list.get(i).getBushou());
                    }
                }

                //将列表所有元素背景设为白色
                for(int i=0;i<bihua_list.size();i++){
                    mRecyclerView1.getChildAt(i).findViewById(R.id.item_pinyinbushou_view_name).setBackgroundColor(Color.WHITE);
                }
                //将选中元素背景设为橙色
                mRecyclerView1.getChildAt(position).findViewById(R.id.item_pinyinbushou_view_name).setBackgroundColor(getResources().getColor(R.color.myoringe,null));

                mRecyclerView2_title.setText(textview.getText().toString()+"画的偏旁部首");
                //刷新adapter，通知界面刷新
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        adapter2.setOnItemClickLitener(new MyRecycleViewAdapter_PinYinBuShou.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView textview= (TextView) view.findViewById(R.id.item_pinyinbushou_view_name);
                String str=textview.getText().toString();
                Intent intent=new Intent(OrderBuShouSearchActivity.this,ShowHanZiResultList.class);
                intent.putExtra("tag","bushou");
                intent.putExtra("key",str);
                intent.putExtra("alllist", (Serializable) all_list);
                intent.putStringArrayListExtra("onekeylist", (ArrayList<String>) onekey_list);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void loadData() {

        String [] list={"一画","二画","三画","四画","五画","六画","七画","八画","九画","十画","十一画","十二画","十三画","十四画","十五画"};
        for (int i=0;i<list.length;i++){
            bihua_list.add(list[i]);
        }
    }

    private void initData() {

        Request request = new Request.Builder()
                .url(" http://v.juhe.cn/xhzd/bushou?dtype=&key=08f921c78a0454069f5938834efa7d4d")
                .build();
        mclient.newCall(request).enqueue(new Callback() {

            JSONObject jsonobject;
            JSONArray jsonArray;
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
                    if(!jsonobject.getString("reason").equals("success")){
                        return;
                    }else{
                        jsonArray=jsonobject.getJSONArray("result");
                        for(int i=0;i<jsonArray.length();i++) {

                            BuShou bushou = new BuShou();
                            JSONObject json = (JSONObject) jsonArray.get(i);
                            bushou.setBihua(json.getString("bihua"));
                            bushou.setBushou(json.getString("bushou"));
                            all_list.add(bushou);
                            Log.i("bushou",bushou.toString());

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OrderBuShouSearchActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<all_list.size();i++){
                            if(all_list.get(i).getBihua().equals("一画")){
                                onekey_list.add(all_list.get(i).getBushou());
                            }
                        }
                        mRecyclerView1.getChildAt(0).findViewById(R.id.item_pinyinbushou_view_name).setBackgroundColor(getResources().getColor(R.color.myoringe,null));
                        adapter2.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    private void initView() {

        back= (ImageView) findViewById(R.id.pinyin_and_bushou_back);
        pagetitle= (TextView) findViewById(R.id.pinyin_and_bushou_title);
        mRecyclerView1= (RecyclerView) findViewById(R.id.pinyin_and_bushou_recyclerview1);
        mRecyclerView2= (RecyclerView) findViewById(R.id.pinyin_and_bushou_recyclerview2);
        mRecyclerView2_title= (TextView) findViewById(R.id.pinyin_and_bushou_recyclerview2_title);
    }
}
