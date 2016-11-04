package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import model.ChengYu_m;
import mysqlite.ChengYuDB;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/4.
 * 首次运行引导页
 */
public class GuideActivity extends AppCompatActivity {

    private ChengYuDB mChengYuDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initData();
    }

    private void initData() {

        mChengYuDB=new ChengYuDB(GuideActivity.this);

        mChengYuDB.insert1(new ChengYu_m("积少成多","JISHAOCHENGDUO"));
        mChengYuDB.insert1(new ChengYu_m("纲举目张","GANGJUKUZHANG"));
        mChengYuDB.insert1(new ChengYu_m("自作主张","ZIZUOZHEZHANG"));
        mChengYuDB.insert1(new ChengYu_m("急敛暴征","JILIANBAOZHENG"));
        mChengYuDB.insert1(new ChengYu_m("鸿骞凤立","HONGSAIFENGLI"));
        mChengYuDB.insert1(new ChengYu_m("贼人心虚","ZIRENXINXU"));
        mChengYuDB.insert1(new ChengYu_m("全无心肝","QUANWUXINGAN"));
        mChengYuDB.insert1(new ChengYu_m("芙蓉出水","CHESHUIFURONG"));
        mChengYuDB.insert1(new ChengYu_m("高耸入云","GAOSONGRUYUN"));
        mChengYuDB.insert1(new ChengYu_m("食案方丈","SHIANFANGAN"));
    }
}
