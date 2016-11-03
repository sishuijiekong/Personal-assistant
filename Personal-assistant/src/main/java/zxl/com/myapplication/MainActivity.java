package zxl.com.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentPagerAdapter;
import model.Joke;
import mysqlite.ChengYuDB;

public class MainActivity extends AppCompatActivity {
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private RadioGroup rgGroup;
    private List<Fragment> fragments;
    private ViewPager mViewPager;
    private ChengYuDB mChengYuDB;
    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);  //隐藏掉系统原先的导航栏
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp_main);

        mChengYuDB=new ChengYuDB(MainActivity.this);

        mChengYuDB.insert1("积少成多");
        mChengYuDB.insert1("纲举目张");
        mChengYuDB.insert1("自作主张");
        mChengYuDB.insert1("急敛暴征");
        mChengYuDB.insert1("鸿骞凤立");
        mChengYuDB.insert1("贼人心虚");
        mChengYuDB.insert1("全无心肝");
        mChengYuDB.insert1("芙蓉出水");
        mChengYuDB.insert1("高耸入云");
        mChengYuDB.insert1("食案方丈");
        fragments=new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(myFragmentPagerAdapter);


        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        rgGroup.check(R.id.rb_home);
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
    }

}
