package activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import model.ChengYu_m;
import mysqlite.MYDB;
import util.InitData;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/4.
 * 首次运行引导页
 */
public class GuideActivity extends AppCompatActivity {

    private MYDB mMYDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initData();
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
