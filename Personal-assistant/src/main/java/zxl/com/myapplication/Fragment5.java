package zxl.com.myapplication;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.PopupWindow;
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
import util.BrightnessUtils;
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
    private boolean isnight=false;
    private PopupWindow mPopupWindow;

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

    /**
     * 接受intent返回的数据并显示在界面上
     * @param requestCode 请求码
     * @param resultCode  返回码
     * @param data  返回数据
     */
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
            case R.id.login_gologin://登录事件

                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.login_yejian://夜间模式
                isnight=!isnight;
                BrightnessUtils.setScrennManualMode(getActivity());
                ContentResolver contentResolver = getActivity().getContentResolver();
                if(!Settings.System.canWrite(getActivity())){
                    Intent intent10 = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                            Uri.parse("package:" + getActivity().getPackageName()));
                    startActivityForResult(intent10, 1);
                }
                if(isnight)
                     {

                        int value = 5; // 设置亮度值为255
                        Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
                        Settings.System.putInt(contentResolver,
                                Settings.System.SCREEN_BRIGHTNESS, value);
                        getActivity().getContentResolver().notifyChange(uri, null);
                        Toast.makeText(getActivity(),"夜间模式,已经开启",Toast.LENGTH_SHORT).show();
                     }else{
                         int value = 255; // 设置亮度值为255
                         Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
                         Settings.System.putInt(contentResolver,
                                 Settings.System.SCREEN_BRIGHTNESS, value);
                         getActivity().getContentResolver().notifyChange(uri, null);
                    Toast.makeText(getActivity(),"日间模式,已经开启",Toast.LENGTH_SHORT).show();
                     }
                break;
            case R.id.login_seting://关于事件
                View popupView = getActivity().getLayoutInflater().inflate(R.layout.aboutme, null);

                mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                mPopupWindow.setTouchable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

                mPopupWindow.getContentView().setFocusableInTouchMode(true);
                mPopupWindow.getContentView().setFocusable(true);
                mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                                mPopupWindow.dismiss();
                                return true;
                        }
                        return false;
                    }

                });
               mPopupWindow.showAtLocation(view,Gravity.CENTER,0,0);
                break;
            case R.id.login_loginout://退出登录

                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());  //先得到构造器
                builder.setTitle("提示"); //设置标题
                builder.setMessage("是否确认清除登录信息并退出?"); //设置内容
                builder.setIcon(R.drawable.logo);//设置图标，图片id即可
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        editor.putBoolean("islogined", false);
                        editor.commit();
                        ShareSDK.initSDK(getActivity());

                        Platform qq = ShareSDK.getPlatform(QQ.NAME);
                        qq.removeAccount(true);
                        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                        weibo.removeAccount(true);
                        dialog.dismiss(); //关闭dialog
                        getActivity().finish();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                //参数都设置完成了，创建并显示出来
                builder.create().show();
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BrightnessUtils.setScrennManualMode2(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        BrightnessUtils.setScrennManualMode2(getActivity());
    }
}
