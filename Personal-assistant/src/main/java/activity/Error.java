package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/4.
 * 错误页面
 */
public class Error extends AppCompatActivity implements View.OnClickListener{

    private ImageView close;
    private ImageView share;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.error);
        initView();
        initEvent();
    }

    private void initEvent() {
        close.setOnClickListener(this);
        share.setOnClickListener(this);

    }

    private void initView() {
        close= (ImageView) findViewById(R.id.error_close);
        share= (ImageView) findViewById(R.id.error_share);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.error_close:
                finish();
                break;
            case R.id.error_share:
                Toast.makeText(this,"分享功能暂代开发！",Toast.LENGTH_SHORT).show();
        }
    }
}
