package activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.ChengYu_m;
import mysqlite.MYDB;
import util.InitData;
import util.ZoomOutPageTransformer;
import zxl.com.myapplication.MainActivity;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/4.
 * 首次运行引导页
 */
public class GuideActivity extends AppCompatActivity {

    private MYDB mMYDB;
    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private MyAdViewPagerAdapter mPagerAdapter;
    private TextView count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.guide);
        mPager = (ViewPager) findViewById(R.id.pager);
        count= (TextView) findViewById(R.id.guide_count);
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView count= (TextView) view.findViewById(R.id.guide_count);
                if(count.getText().equals("开始使用")){
                    Intent intent=new Intent(GuideActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        ArrayList<ImageView> imageViews= new ArrayList();
//        for(int i = 0;i<6;i++){
//            ImageView imageView = new ImageView(GuideActivity.this);
//            imageView.setImageResource(R.mipmap.guide1);
//            imageViews.add(imageView);
//        }
        ImageView imageView1 = new ImageView(GuideActivity.this);
        imageView1.setImageResource(R.mipmap.guidehanzi1);
        imageViews.add(imageView1);

        ImageView imageView2 = new ImageView(GuideActivity.this);
        imageView2.setImageResource(R.mipmap.guidehanzi2);
        imageViews.add(imageView2);

        ImageView imageView3 = new ImageView(GuideActivity.this);
        imageView3.setImageResource(R.mipmap.guidechengyu1);
        imageViews.add(imageView3);

        ImageView imageView4 = new ImageView(GuideActivity.this);
        imageView4.setImageResource(R.mipmap.guidechengyu2);
        imageViews.add(imageView4);

        ImageView imageView5 = new ImageView(GuideActivity.this);
        imageView5.setImageResource(R.mipmap.guidexiaohua);
        imageViews.add(imageView5);

        ImageView imageView6 = new ImageView(GuideActivity.this);
        imageView6.setImageResource(R.mipmap.guidexinwen);
        imageViews.add(imageView6);



        mPager.setBackgroundColor(Color.WHITE);
        mPagerAdapter = new MyAdViewPagerAdapter(GuideActivity.this,imageViews);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageMargin(-100);
        mPager.setPageTransformer(true,new ZoomOutPageTransformer(mPager));

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position==4){
                    count.setText("开始使用");
                    count.setTextColor(Color.RED);
                }else{
                    count.setText("下一页");
                    count.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initData();
    }



    public class MyAdViewPagerAdapter extends PagerAdapter {

        private ArrayList<ImageView> pagerList;
        private Context ctx;

        public MyAdViewPagerAdapter(Context ctx, ArrayList<ImageView> pagerList) {
            this.ctx = ctx;
            this.pagerList = pagerList;
        }

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(pagerList.get(position));
            return pagerList.get(position);
        }

        @Override
        public void destroyItem(View container, int position, Object view) {
            ((ViewPager) container).removeView(pagerList.get(position));
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public void startUpdate(View arg0) {
        }

        @Override
        public void finishUpdate(View arg0) {
        }



    }

    private void initData() {

        mMYDB =new MYDB(GuideActivity.this);
        InitData.SetDataChengYu(this);
        InitData.SetDataDuoBiHua(this);
        InitData.SetDataSanGe(this);

        InitData.SetDataSiGe(this);
        InitData.SetDataShangXia(this);
        InitData.SetDataZuoYou(this);

    }
}
