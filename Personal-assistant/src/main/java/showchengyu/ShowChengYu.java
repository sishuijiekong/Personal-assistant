package showchengyu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import model.ChengYu;
import model.New;
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
                    @Override
                    public void run() {

                        if(chengyudata!=null){
                        chengyu.setText(chengyudata.getName());

                            if(chengyudata.getPinyin().equals("null")){
                                pinyin.setText("拼音："+"还没有拼音(⊙o⊙)哦！");
                            }else{
                                pinyin.setText("拼音："+chengyudata.getPinyin());
                            }

                            if(chengyudata.getChengyujs().equals("null")){
                                jieeshi.setText("还没解释(⊙o⊙)哦！");
                            }else{
                                jieeshi.setText(chengyudata.getChengyujs());
                            }

                            if(chengyudata.getFrom_().equals("null")){
                                chuchu.setText("找不到出处(⊙o⊙)哦！");
                            }else{
                                chuchu.setText(chengyudata.getFrom_());
                            }

                            if(chengyudata.getExample().equals("null")){
                                liju.setText("还没有准备例句(⊙o⊙)哦！");
                            }else{
                                liju.setText(chengyudata.getExample());
                            }

                            if(chengyudata.getCiyujs().equals("null")){
                                yinwen.setText("还没英文解释(⊙o⊙)哦！");
                            }else{
                                yinwen.setText(chengyudata.getCiyujs());
                            }

                            if(chengyudata.getYinzhengjs().equals("null")){
                                yinzheng.setText("找不到引证例子(⊙o⊙)哦！");
                            }else{
                                yinzheng.setText(chengyudata.getYinzhengjs());
                            }

                        }else{
                            setContentView(R.layout.error);
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.showchengyu_back:
                finish();
                break;
            case R.id.showchengyu_share:
                break;
            case R.id.showchengyu_shoucang:
                break;

        }
    }
}
