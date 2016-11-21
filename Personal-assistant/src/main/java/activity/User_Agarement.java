package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/10/29.
 */
public class User_Agarement extends AppCompatActivity implements View.OnClickListener {

    private ImageView agreement;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.user_agreement);
        agreement= (ImageView) findViewById(R.id.agreement_cancel);
        agreement.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
