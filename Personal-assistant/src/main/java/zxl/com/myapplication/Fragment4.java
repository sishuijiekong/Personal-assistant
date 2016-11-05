package zxl.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 张显林 on 2016/8/11.
 * 新华字典首页
 */
public class Fragment4 extends Fragment  implements  View.OnClickListener{

    //收藏按钮
    private ImageView shoucang;
    //问题解答按钮
    private ImageView question;
    //搜索输入框
    private EditText search;
    //按拼音搜索
    private View pinyinsearch;
    //按部首搜索
    private View bushousearch;
    //趣味汉字
    private TextView quweihanzi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment4, container, false);

        shoucang= (ImageView) view.findViewById(R.id.fragment4_shoucang);
        question= (ImageView) view.findViewById(R.id.fragment4_question);
        search= (EditText) view.findViewById(R.id.fragment4_search);
        quweihanzi= (TextView) view.findViewById(R.id.fragment4_quwei);
        pinyinsearch=view.findViewById(R.id.fragment4_pin);
        bushousearch=view.findViewById(R.id.fragment4_bu);


        shoucang.setOnClickListener(this);
        question.setOnClickListener(this);
        search.setOnClickListener(this);
        quweihanzi.setOnClickListener(this);
        pinyinsearch.setOnClickListener(this);
        bushousearch.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment4_shoucang:
                Toast.makeText(getActivity(),"收藏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment4_question:
                Toast.makeText(getActivity(),"解答",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment4_search:
                Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment4_pin:
                Toast.makeText(getActivity(),"拼音",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment4_bu:
                Toast.makeText(getActivity(),"部首",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment4_quwei:
                Toast.makeText(getActivity(),"趣味",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
