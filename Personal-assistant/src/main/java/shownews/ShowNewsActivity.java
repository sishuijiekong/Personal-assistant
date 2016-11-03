package shownews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import zxl.com.myapplication.R;

/**
 * Created by 28618 on 2016/11/2.
 */
public class ShowNewsActivity extends AppCompatActivity implements View.OnClickListener{

    private WebView webView;
    private ImageView close;
    private TextView  like;
    private TextView  unlike;
    private Random random=new Random();
    private int x;
    private ProgressDialog mpDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.shownews);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        webView= (WebView) findViewById(R.id.shownews);
        close= (ImageView) findViewById(R.id.shownews_close);
        like= (TextView) findViewById(R.id.shownews_like);
        unlike= (TextView) findViewById(R.id.shownews_unlike);

        like.setOnClickListener(this);
        unlike.setOnClickListener(this);
        close.setOnClickListener(this);

        mpDialog = new ProgressDialog(ShowNewsActivity.this);
        mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条
        mpDialog.setMessage("新闻正在赶来....");
        mpDialog.setIndeterminate(false);//设置进度条是否为不明确
        mpDialog.show();
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mpDialog.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }
        });
        initData();
    }

    private void initData() {
        x=1+random.nextInt(100);
        like.setText(x+"%赞成");
        unlike.setText((100-x)+"%不赞成");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shownews_close:
                finish();
                break;
            case R.id.shownews_like:
                double y=(1+random.nextInt(10))/100;
                like.setText(((x+y)+"%赞成"));
                unlike.setTextColor(getResources().getColor(R.color.myblack,null));
                unlike.setText(((100-x-y)+"%不赞成"));
                like.setTextColor(getResources().getColor(R.color.myoringe,null));
                break;
            case R.id.shownews_unlike:
                double y2=(1+random.nextInt(10))/100;
                like.setText(((x+y2)+"%赞成"));
                unlike.setText(((100-x-y2)+"%不赞成"));
                like.setTextColor(getResources().getColor(R.color.myblack,null));
                unlike.setTextColor(getResources().getColor(R.color.myoringe,null));
                break;
        }
    }
}
