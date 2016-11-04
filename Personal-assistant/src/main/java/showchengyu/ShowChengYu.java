package showchengyu;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
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

import model.ChengYu;
import model.ChengYu_m;
import model.New;
import mysqlite.ChengYuDB;
import util.ChengYuFormate;
import zxl.com.myapplication.R;

/**
 * Created by Administrator on 2016/11/3.
 */
public class ShowChengYu  extends AppCompatActivity implements View.OnClickListener{

    private String name;
    private ImageView back;
    private ImageView shoucang;
    private ImageView share;
    private TextView chengyu;
    private TextView pinyin;
    private RadioGroup mRadioGroup1;
    private RadioGroup mRadioGroup2;
    private TextView jieeshi;
    private TextView chuchu;
    private TextView liju;
    private TextView yinwen;
    private TextView yinzheng;
    private ChengYu chengyudata = new ChengYu();
    private ImageView error_close;
    private ImageView error_share;

    private ChengYuDB mChengYuDB=new ChengYuDB(this);

    private static OkHttpClient client=new OkHttpClient();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        setContentView(R.layout.showchengyu);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

        back.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    private void initView() {

        back= (ImageView) findViewById(R.id.showchengyu_back);
        shoucang=(ImageView) findViewById(R.id.showchengyu_shoucang);
        share=(ImageView) findViewById(R.id.showchengyu_share);
        chengyu= (TextView) findViewById(R.id.showchengyu_name);
        pinyin= (TextView) findViewById(R.id.showchengyu_pingyin);
        mRadioGroup1= (RadioGroup) findViewById(R.id.showchengyu_radio_tongyi);
        mRadioGroup2= (RadioGroup) findViewById(R.id.showchengyu_radio_fanyi);
        jieeshi= (TextView) findViewById(R.id.showchengyu_jieshi);
        chuchu= (TextView) findViewById(R.id.showchengyu_chuchu);
        liju= (TextView) findViewById(R.id.showchengyu_liju);
        yinwen= (TextView) findViewById(R.id.showchengyu_jieshi_yingwen);
        yinzheng= (TextView) findViewById(R.id.showchengyu_yinzheng);

    }

    private void initData() {


        Request request = new Request.Builder()
                .url("http://v.juhe.cn/chengyu/query?key=5fa39047e1ed13b373221ed8ca7ff783&word="+name+"")
                .build();

        client.newCall(request).enqueue(new Callback() {

            JSONObject jsonobject;
            JSONObject jsonobject2;
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
                    Log.i("chengyu",s);
                    jsonobject=new JSONObject(s);
                    if(jsonobject.getString("reason").equals("success")) {
                        jsonobject2 = new JSONObject(jsonobject.getString("result"));
                        chengyudata.setName(name);
                        chengyudata.setChengyujs(jsonobject2.getString("chengyujs"));
                        chengyudata.setCiyujs(jsonobject2.getString("ciyujs"));
                        chengyudata.setExample(jsonobject2.getString("example"));
                        chengyudata.setPinyin(jsonobject2.getString("pinyin"));
                        chengyudata.setTongyi(jsonobject2.getString("tongyi"));
                        chengyudata.setFanyi(jsonobject2.getString("fanyi"));
                        chengyudata.setYinzhengjs(jsonobject2.getString("yinzhengjs"));
                        chengyudata.setFrom_(jsonobject2.getString("from_"));
                    }else{
                        chengyudata=null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ShowChengYu.this.runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void run() {

                        if(chengyudata!=null){
                             //往成语学习足迹表里插入一条数据
                        mChengYuDB.insert2(new ChengYu_m(chengyudata.getName(),chengyudata.getPinyin()));
                             //往页面控件设置数据
                        chengyu.setText(chengyudata.getName());
                            //设置同义成语 （如果数据中没有同义成语，则显示提示语句）
                            if(chengyudata.getTongyi().equals("null")){
                                TextView textView = new TextView(ShowChengYu.this);
                                textView.setText("没有同义成语");
                                mRadioGroup1.addView(textView);
                            }else{
                                TextView textView;
                                final List<String> stringList;
                                stringList= ChengYuFormate.formate1(chengyudata.getTongyi().toString());
                                for(int i=0;i<stringList.size();i++){
                                    textView = new TextView(ShowChengYu.this);
                                    textView.setText(stringList.get(i).toString());
                                    textView.setTextColor(android.graphics.Color.parseColor("#1e8ae8"));
                                    mRadioGroup1.addView(textView);
                                    final int finalI = i;
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent=new Intent(ShowChengYu.this,ShowChengYu.class);
                                            intent.putExtra("name",stringList.get(finalI).toString());
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }
                            }

                            //设置反义成语 （如果数据中没有反义成语，则显示提示语句）
                            if(chengyudata.getFanyi().equals("null")){

                                TextView textView = new TextView(ShowChengYu.this);
                                textView.setText("没有反义成语");
                                mRadioGroup2.addView(textView);

                            }else{
                                TextView textView;
                                final List<String> stringList;
                                stringList= ChengYuFormate.formate1(chengyudata.getFanyi().toString());
                                for(int i=0;i<stringList.size();i++){
                                    textView = new TextView(ShowChengYu.this);
                                    textView.setText(stringList.get(i).toString());
                                    textView.setTextColor(android.graphics.Color.parseColor("#1e8ae8"));
                                    mRadioGroup2.addView(textView);
                                    final int finalI = i;
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent=new Intent(ShowChengYu.this,ShowChengYu.class);
                                            intent.putExtra("name",stringList.get(finalI).toString());
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }
                            }

                            //设置成语拼音 （如果数据中没有成语拼音，则显示提示语句）
                            if(chengyudata.getPinyin().equals("null")){
                                pinyin.setText("拼音  ："+"还没有拼音(⊙o⊙)哦！");
                            }else{
                                pinyin.setText("拼音  ："+chengyudata.getPinyin());
                            }

                            //设置成语解释
                            if(chengyudata.getChengyujs().equals("null")){
                                jieeshi.setText("还没解释(⊙o⊙)哦！");
                            }else{
                                jieeshi.setText(chengyudata.getChengyujs());
                            }

                            //设置成语出处
                            if(chengyudata.getFrom_().equals("null")){
                                chuchu.setText("找不到出处(⊙o⊙)哦！");
                            }else{
                                chuchu.setText(chengyudata.getFrom_());
                            }

                            //设置例句
                            if(chengyudata.getExample().equals("null")){
                                liju.setText("还没有准备例句(⊙o⊙)哦！");
                            }else{
                                liju.setText(chengyudata.getExample());
                            }

                            //设置英文解释
                            if(chengyudata.getCiyujs().equals("null")){
                                yinwen.setText("还没英文解释(⊙o⊙)哦！");
                            }else{
                                yinwen.setText(chengyudata.getCiyujs());
                            }

                            //设置引证例子
                            if(chengyudata.getYinzhengjs().equals("null")){
                                yinzheng.setText("找不到引证例子(⊙o⊙)哦！");
                            }else{
                                yinzheng.setText(chengyudata.getYinzhengjs());
                            }

                        }else{
                            //没有数据 显示 no data 页面
                            setContentView(R.layout.error);
                            error_close= (ImageView) findViewById(R.id.error_close);
                            error_share= (ImageView) findViewById(R.id.error_share);
                            error_close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finish();
                                }
                            });
                            error_share.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(ShowChengYu.this,"分享功能暂代开发！",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.showchengyu_back://返回按钮
                finish();
                break;
            case R.id.showchengyu_share://分享按钮
                break;
            case R.id.showchengyu_shoucang://收藏按钮
                mChengYuDB.insert3(new ChengYu_m(chengyu.getText().toString(),pinyin.getText().toString()));
                Toast.makeText(this,"已加入收藏夹",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
