package zxl.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.List;

import activity.GuideActivity;
import activity.LoginActivity;
import adapter.MyFragmentPagerAdapter;
import mysqlite.MYDB;

/**
 *  by 张显林
 *  程序主页面
 */
public class MainActivity extends AppCompatActivity {
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private RadioGroup rgGroup;
    private List<Fragment> fragments;
    private ViewPager mViewPager;
    private MYDB mMYDB;
    private Cursor mCursor;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);  //隐藏掉系统原先的导航栏
        setContentView(R.layout.activity_main);

        // 读取 SharedPreferences
        preferences = getSharedPreferences("APPLOG", MODE_PRIVATE);
        //判断是不是首次运行，
        if(preferences.getBoolean("isfirstrun", true)){

            editor = preferences.edit();
            //将首次运行标志位设置为false，下次运行时不在显示引导界面
            editor.putBoolean("isfirstrun", false);
            editor.commit();

            Intent intent=new Intent(MainActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
        }
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        fragments=new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(myFragmentPagerAdapter);


        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        rgGroup.check(R.id.rb_service);
        //当点击底部按钮时切换页面
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_home) {
                    mViewPager.setCurrentItem(0, false);//去掉切换页面的动画
                } else if (i == R.id.rb_news) {
                    mViewPager.setCurrentItem(1, false);
                } else if (i == R.id.rb_service) {
                    mViewPager.setCurrentItem(2, false);
                } else if (i == R.id.rb_gov) {
                    mViewPager.setCurrentItem(3, false);
                } else if (i == R.id.rb_setting) {
                    mViewPager.setCurrentItem(4, false);
                }

            }
        });
        //防止频繁的销毁视图
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setCurrentItem(2);
    }

}
