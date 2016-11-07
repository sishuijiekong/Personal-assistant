package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/5.
 * 趣味汉字页面
 */
public class QuWeiHanZiActivity extends AppCompatActivity  implements View.OnClickListener{

    private View bihuazuiduo;
    private View zuoyouxiangtong;
    private View shangxiaxiangtong;
    private View sangexiangtong;
    private View sigexiangtong;
    private ImageView back;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.quweihanzi);
        back= (ImageView) findViewById(R.id.quweihanzi_back);
        bihuazuiduo=findViewById(R.id.quweihanzi_duobijua);
        sigexiangtong=findViewById(R.id.quweihanzi_sigexiangtong);
        sangexiangtong=findViewById(R.id.quweihanzi_sangexiangtong);
        shangxiaxiangtong=findViewById(R.id.quweihanzi_shangxiaxiangtong);
        zuoyouxiangtong=findViewById(R.id.quweihanzi_zuoyouxiangtong);


        back.setOnClickListener(this);
        bihuazuiduo.setOnClickListener(this);
        sigexiangtong.setOnClickListener(this);
        sangexiangtong.setOnClickListener(this);
        shangxiaxiangtong.setOnClickListener(this);
        zuoyouxiangtong.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.quweihanzi_back:
                finish();
                break;
            case R.id.quweihanzi_duobijua:
                intent=new Intent(this,ShowQuWeiHanZiActivity.class);
                intent.putExtra("type","0");
                startActivity(intent);
                break;
            case R.id.quweihanzi_shangxiaxiangtong:
                intent=new Intent(this,ShowQuWeiHanZiActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
                break;
            case R.id.quweihanzi_zuoyouxiangtong:
                intent=new Intent(this,ShowQuWeiHanZiActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.quweihanzi_sangexiangtong:
                intent=new Intent(this,ShowQuWeiHanZiActivity.class);
                intent.putExtra("type","3");
                startActivity(intent);
                break;
            case R.id.quweihanzi_sigexiangtong:
                intent=new Intent(this,ShowQuWeiHanZiActivity.class);
                intent.putExtra("type","4");
                startActivity(intent);
                break;
        }
    }
}
