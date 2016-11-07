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

        mMYDB.insert1(new ChengYu_m("积少成多","JISHAOCHENGDUO"));
        mMYDB.insert1(new ChengYu_m("纲举目张","GANGJUKUZHANG"));
        mMYDB.insert1(new ChengYu_m("自作主张","ZIZUOZHEZHANG"));
        mMYDB.insert1(new ChengYu_m("急敛暴征","JILIANBAOZHENG"));
        mMYDB.insert1(new ChengYu_m("鸿骞凤立","HONGSAIFENGLI"));
        mMYDB.insert1(new ChengYu_m("贼人心虚","ZIRENXINXU"));
        mMYDB.insert1(new ChengYu_m("全无心肝","QUANWUXINGAN"));
        mMYDB.insert1(new ChengYu_m("芙蓉出水","CHESHUIFURONG"));
        mMYDB.insert1(new ChengYu_m("高耸入云","GAOSONGRUYUN"));
        mMYDB.insert1(new ChengYu_m("食案方丈","SHIANFANGAN"));


        InitData.SetDataDuoBiHua(this);
        InitData.SetDataSanGe(this);

        InitData.SetDataSiGe(this);
        InitData.SetDataShangXia(this);
        InitData.SetDataZuoYou(this);

    }
}
