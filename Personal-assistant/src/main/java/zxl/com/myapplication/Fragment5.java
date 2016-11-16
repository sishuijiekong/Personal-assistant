package zxl.com.myapplication;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import org.junit.internal.runners.statements.ExpectException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import activity.LoginActivity;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import util.MyDateFormate;

/**
 * Created by 张显林 on 2016/8/11.
 * 个人信息页面和设置页面
 */
public class Fragment5 extends Fragment implements View.OnClickListener{
    private View view;
    private View view2;
    private ImageView touxiang;
    private TextView name;
    private TextView jianjie;
    private TextView sex;
    private TextView address;
    private TextView registertime;
    private TextView lasttime;
    private View yejianmoshi;
    private TextView shezhi;
    private TextView zhuxiao;
    private SharedPreferences mSharedPreferences;
    private OkHttpClient client;
    private Picasso picasso;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment5, container, false);

        client=new OkHttpClient();
        mSharedPreferences=getActivity().getSharedPreferences("APPLOG", Context.MODE_PRIVATE);
        view2= view.findViewById(R.id.login_gologin);
        touxiang= (ImageView) view.findViewById(R.id.login_pic);
        name= (TextView) view.findViewById(R.id.login_name);
        jianjie= (TextView) view.findViewById(R.id.login_jianjie);
        sex= (TextView) view.findViewById(R.id.login_sex);
        address= (TextView) view.findViewById(R.id.login_address);
        registertime= (TextView) view.findViewById(R.id.login_time);
        lasttime= (TextView) view.findViewById(R.id.login_lasttime);
        yejianmoshi= view.findViewById(R.id.login_yejian);
        shezhi= (TextView) view.findViewById(R.id.login_seting);
        zhuxiao= (TextView) view.findViewById(R.id.login_loginout);
        view2.setOnClickListener(this);
        yejianmoshi.setOnClickListener(this);
        shezhi.setOnClickListener(this);
        zhuxiao.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode==1){
        name.setText(data.getStringExtra("name"));
        jianjie.setText(data.getStringExtra("jianjie"));

            Log.i("sex",data.getStringExtra("sex").toString());
            if(data.getStringExtra("sex").toString().equals("m")||data.getStringExtra("sex").toString().equals("男")){
                sex.setText("男");
            }else{
                sex.setText("女");
            }

        address.setText(data.getStringExtra("address"));

            registertime.setText(MyDateFormate.formatetime(mSharedPreferences.getString("firsttime","Mon Nov 14 02:44:11 GMT+00:00 2016").toString()));
            lasttime.setText("上次登录时间："+ MyDateFormate.formatetime(mSharedPreferences.getString("lastlogintime","Mon Nov 14 02:44:11 GMT+00:00 2016").toString()));
            Picasso.with(getActivity()).load(data.getStringExtra("touxiang")).into(touxiang);


            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean("islogined", true);
            editor.putString("lastlogintime", new Date().toString());
            editor.commit();


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_gologin:

                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.login_yejian:

                Toast.makeText(getActivity(),"夜间模式,暂代开发",Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_seting:
                Toast.makeText(getActivity(),"App设置,暂代开发",Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_loginout:
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putBoolean("islogined", false);
                editor.commit();
                ShareSDK.initSDK(getActivity());

                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.removeAccount(true);
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.removeAccount(true);
                Toast.makeText(getActivity(),"已经清除您的登录信息！",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
