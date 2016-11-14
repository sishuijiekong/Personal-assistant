package activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import zxl.com.myapplication.Fragment5;
import zxl.com.myapplication.MainActivity;
import zxl.com.myapplication.R;

/**
 * Created by 28618 on 2016/10/24.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private OkHttpClient client;
    private ImageView back;
    private EditText username;
    private EditText pasword;
    private Button  login;
    private Button  register;
    private Button  getpassword;
    private Button  login_for_weixin;
    private Button  login_for_qq;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;
    private  Platform weibo;
    private  Platform qq;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.login);
        ShareSDK.initSDK(this);
        weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        qq = ShareSDK.getPlatform(QQ.NAME);
        initView();
        initEvent();
    }

    private void initEvent() {
        back.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        getpassword.setOnClickListener(this);
        login_for_weixin.setOnClickListener(this);
        login_for_qq.setOnClickListener(this);

    }

    private void initView() {
        back= (ImageView) findViewById(R.id.login_back);
        username= (EditText) findViewById(R.id.login_username);
        pasword= (EditText) findViewById(R.id.login_password);
        login= (Button) findViewById(R.id.login_login);
        register= (Button) findViewById(R.id.login_register);
        getpassword= (Button) findViewById(R.id.login_getpassword);
        login_for_weixin= (Button) findViewById(R.id.login_for_weixin);
        login_for_qq= (Button) findViewById(R.id.login_for_qq);

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_back://返回
                    finish();
                case R.id.login_login://登陆
                    break;
                case R.id.login_register://注册
                    Intent intent=new Intent(LoginActivity.this,Register.class);
                    startActivity(intent);
                    break;
                case R.id.login_getpassword://忘记密码

                    break;
                case R.id.login_for_qq://用QQ登陆
                    //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                    qq.setPlatformActionListener(new PlatformActionListener() {

                        @Override
                        public void onError(Platform arg0, int arg1, Throwable arg2) {
                            // TODO Auto-generated method stub
                            arg2.printStackTrace();
                        }

                        @Override
                        public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                            // TODO Auto-generated method stub
                            //输出所有授权信息
                            arg0.getDb().exportData();
                            Log.d("tag++++++",arg2.toString());

                            Intent aintent = new Intent(LoginActivity.this, Fragment5.class);
                            aintent.putExtra("code","success");
                            aintent.putExtra("name",arg2.get("nickname").toString());
                            aintent.putExtra("jianjie","您还没有个人简介哦");
                            aintent.putExtra("sex",arg2.get("gender").toString());
                            aintent.putExtra("address",arg2.get("city").toString());
                            aintent.putExtra("touxiang",arg2.get("figureurl_qq_1").toString());
                            setResult(1, aintent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                            finish();//此处一定要调用finish()方法
                        }

                        @Override
                        public void onCancel(Platform arg0, int arg1) {
                            // TODO Auto-generated method stub

                        }
                    });
                    qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                    qq.showUser(null);//授权并获取用户信息
                    break;
                case R.id.login_for_weixin://用微博登陆

                    //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                    weibo.setPlatformActionListener(new PlatformActionListener() {

                        @Override
                        public void onError(Platform arg0, int arg1, Throwable arg2) {
                            // TODO Auto-generated method stub
                            arg2.printStackTrace();
                        }

                        @Override
                        public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                            // TODO Auto-generated method stub
                            //输出所有授权信息
                            arg0.getDb().exportData();
                            Log.d("tag++++++",arg2.toString());
                            Intent aintent = new Intent(LoginActivity.this, Fragment5.class);
                            aintent.putExtra("code","success");
                            aintent.putExtra("name",arg2.get("screen_name").toString());
                            aintent.putExtra("jianjie",arg2.get("description").toString());
                            aintent.putExtra("sex",arg2.get("gender").toString());
                            aintent.putExtra("address",arg2.get("location").toString());
                            aintent.putExtra("touxiang",arg2.get("profile_image_url").toString());
                            setResult(1, aintent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                            finish();//此处一定要调用finish()方法
                        }

                        @Override
                        public void onCancel(Platform arg0, int arg1) {
                            // TODO Auto-generated method stub

                        }
                    });
                    weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                    weibo.showUser(null);//授权并获取用户信息

                    break;
                default:
                    break;
            }
    }
}
