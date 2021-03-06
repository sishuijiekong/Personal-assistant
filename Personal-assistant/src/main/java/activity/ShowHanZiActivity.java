package activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import mysqlite.MYDB;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/3.
 * 显示成语详细信息界面
 */
public class ShowHanZiActivity extends AppCompatActivity implements View.OnClickListener{

    private String name;
    //返回按钮
    private ImageView back;
    //收藏按钮
    private ImageView shoucang;
    //分享按钮
    private ImageView share;
    //汉字名称
    private TextView hanzi;
    //汉字拼音
    private TextView pinyin;
    //汉字部首
    private  TextView bushou;
    //汉字五笔
    private  TextView wubi;
    //汉字笔画
    private  TextView bihua;

    //汉字基本信息
    private  TextView jiben;
    //汉字详细信息
    private  TextView xiangxi;

    //没有数据时页面的返回按钮
    private ImageView error_close;
    //没有数据时页面的反响按钮
    private ImageView error_share;

    private TextView message;
    //数据库实例
    private MYDB mMYDB =new MYDB(this);
    //网络连接请求
    private static OkHttpClient client=new OkHttpClient();

    //本地文件读取
    private SharedPreferences preferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        setContentView(R.layout.showhanzi);
        ShareSDK.initSDK(this);
        initView();
        initData();
        initEvent();
    }

    /**
     * 控件事件监听
     */
    private void initEvent() {

        back.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    /**
     * 实例化控件
     */
    private void initView() {

        back= (ImageView) findViewById(R.id.showhanzi_back);
        shoucang=(ImageView) findViewById(R.id.showhanzi_shoucang);
        share=(ImageView) findViewById(R.id.showhanzi_share);
        hanzi= (TextView) findViewById(R.id.showhanzi_zi);
        pinyin= (TextView) findViewById(R.id.showhanzi_pinyin);
        bushou= (TextView) findViewById(R.id.showhanzi_bushou);
        wubi= (TextView) findViewById(R.id.showhanzi_wubi);
        bihua= (TextView) findViewById(R.id.showhanzi_bihua);
        jiben= (TextView) findViewById(R.id.showhanzi_jiben);
        xiangxi= (TextView) findViewById(R.id.showhanzi_xiangxi);



    }

    /**
     * 加载数据
     */
    private void initData() {


        Request request = new Request.Builder()
                .url("http://v.juhe.cn/xhzd/query?word="+name+"&dtype=&key=08f921c78a0454069f5938834efa7d4d")
                .build();

        client.newCall(request).enqueue(new Callback() {

            JSONObject jsonobject;
            JSONObject jsonobject2;
            int x=0;
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
                    if(jsonobject.getString("reason").equals("返回成功")) {
                        jsonobject2 = new JSONObject(jsonobject.getString("result"));
                        x=1;
                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ShowHanZiActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(x==1){
                        try {
                            hanzi.setText(jsonobject2.getString("zi"));
                            pinyin.setText(jsonobject2.getString("pinyin"));
                            bushou.setText(jsonobject2.getString("bushou"));
                            bihua.setText(jsonobject2.getString("bihua"));
                            wubi.setText(jsonobject2.getString("wubi"));
                            jiben.setText(jsonobject2.getString("jijie"));
                            xiangxi.setText(jsonobject2.getString("xiangjie"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else{
                           setContentView(R.layout.error2);
                            error_close= (ImageView) findViewById(R.id.error_close);
                            error_share= (ImageView) findViewById(R.id.error_share);
                            message=(TextView) findViewById(R.id.error_massage);
                            message.setText("我们还没有收录你要查询的汉字");
                            error_close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finish();
                                }
                            });
                            error_share.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(ShowHanZiActivity.this,"分享功能暂代开发！",Toast.LENGTH_SHORT).show();
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
            case R.id.showhanzi_back://返回按钮
                finish();
                break;
            case R.id.showhanzi_share://分享按钮

                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
                oks.setTitle("值得收藏的成语");
                // titleUrl是标题的网络链接，QQ和QQ空间等使用
                //oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("汉字："+hanzi.getText()+"拼音："+pinyin.getText()+"基本信息"+jiben.getText());
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                //oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("汉字："+hanzi.getText()+"拼音："+pinyin.getText()+"基本信息"+jiben.getText());
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                //oks.setSiteUrl("http://sharesdk.cn");

                // 启动分享GUI
                oks.show(ShowHanZiActivity.this);

                break;
            case R.id.showhanzi_shoucang://收藏按钮
                preferences=getSharedPreferences("APPLOG", Context.MODE_APPEND);
                if (preferences.getBoolean("islogined",false)){
                    mMYDB.hanzishoucanginsert(hanzi.getText().toString(),pinyin.getText().toString());
                    Toast.makeText(this,"已加入收藏夹",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"你还没有登录，请登录后再收藏！",Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
