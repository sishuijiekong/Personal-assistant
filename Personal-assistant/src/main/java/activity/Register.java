package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.ServiceWorkerClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.okhttp.OkHttpClient;
import zxl.com.myapplication.R;

/**
 * Created by 28618 on 2016/10/29.
 */
public class Register extends AppCompatActivity implements View.OnClickListener{

    private OkHttpClient client;
    private ImageView register_cancel;
    private EditText user_name;
    private EditText user_pwd;
    private EditText user_pwd2;
    private EditText user_email;
    private EditText user_tel;
    private Button sure;
    private TextView agreement;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.register);
        initView();
        initEvent();
    }

    private void initEvent() {
        register_cancel.setOnClickListener(this);
        sure.setOnClickListener(this);
        agreement.setOnClickListener(this);
    }

    private void initView() {
       register_cancel= (ImageView) findViewById(R.id.register_cancel);
        user_name= (EditText) findViewById(R.id.register_name);
       user_pwd= (EditText) findViewById(R.id.register_pwd);
       user_pwd2= (EditText) findViewById(R.id.register_pwd2);
       user_email= (EditText) findViewById(R.id.register_email);
       user_tel= (EditText) findViewById(R.id.register_tel);
       sure= (Button) findViewById(R.id.register_sure);
       agreement= (TextView) findViewById(R.id.agreement);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_cancel:
                finish();
                break;
            case R.id.register_sure:
                Toast.makeText(this,"确认注册",Toast.LENGTH_SHORT).show();
                break;
            case R.id.agreement:
                Intent intent=new Intent(Register.this,User_Agarement.class);
                startActivity(intent);
               // Toast.makeText(this,"阅读使用协议",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
