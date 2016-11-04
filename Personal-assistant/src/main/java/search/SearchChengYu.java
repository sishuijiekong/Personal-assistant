package search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import showchengyu.ShowChengYu;
import zxl.com.myapplication.R;

/**
 * Created by Administrator on 2016/11/4.
 */
public class SearchChengYu extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private EditText name;
    private Button ok;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.search);
        back= (ImageView) findViewById(R.id.search_back);
        name= (EditText) findViewById(R.id.search_text);
        ok= (Button) findViewById(R.id.search_ok);

        back.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_back:
                finish();
                break;
            case R.id.search_ok:
                if(name.getText().toString()==null||name.getText().toString().length()==0){
                    Toast.makeText(this,"请输入搜索内容！",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(SearchChengYu.this, ShowChengYu.class);
                    intent.putExtra("name",name.getText().toString());
                    startActivity(intent);
                    finish();
                }

        }
    }

}
