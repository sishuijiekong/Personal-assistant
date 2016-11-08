package zxl.com.myapplication;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.Date;

import activity.LoginActivity;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

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
    private TextView yejianmoshi;
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
        yejianmoshi= (TextView) view.findViewById(R.id.login_yejian);
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
            if(data.getStringExtra("sex").toString().equals("m")){
                sex.setText("男");
            }else{
                sex.setText("女");
            }

        address.setText(data.getStringExtra("address"));

            registertime.setText(mSharedPreferences.getString("firsttime","2016-10-13"));
            lasttime.setText("上次登录时间："+mSharedPreferences.getString("lastlogintime","2016-11-13").toString());
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
            case R.id.login_jianjie:
                break;
            case R.id.login_seting:

                break;
            case R.id.login_loginout:
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putBoolean("islogined", false);
                editor.commit();
                ShareSDK.initSDK(getActivity());
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.removeAccount(true);
                getActivity().finish();
                break;
        }
    }
}
