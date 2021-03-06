package zxl.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import activity.LoginActivity;
import activity.OrderBuShouSearchActivity;
import activity.OrderPinYinSearchActivity;
import activity.QuWeiHanZiActivity;
import activity.SearchHanZiActivity;
import activity.ShowHanZiActivity;
import activity.ShowHanZiResultList;
import activity.ShowShouCangHanZiListActivity;

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

    private SharedPreferences preferences;

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
                preferences=getActivity().getSharedPreferences("APPLOG", Context.MODE_APPEND);
                if(preferences.getBoolean("islogined",false)){
                Intent intent7=new Intent(getActivity(), ShowShouCangHanZiListActivity.class);
                startActivity(intent7);
                }else{
                    Toast.makeText(getActivity(),"您还没有登录，请登录后查看",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.fragment4_question:
                Toast.makeText(getActivity(),"解答",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment4_search:

                Intent intent4=new Intent(getActivity(), SearchHanZiActivity.class);
                startActivity(intent4);
                break;
            case R.id.fragment4_pin:
                Intent intent=new Intent(getActivity(), OrderPinYinSearchActivity.class);
                intent.putExtra("title","拼音查字");
                startActivity(intent);
                break;
            case R.id.fragment4_bu:
                Intent intent2=new Intent(getActivity(), OrderBuShouSearchActivity.class);
                intent2.putExtra("title","部首查字");
                startActivity(intent2);
                break;
            case R.id.fragment4_quwei:
                Intent intent12=new Intent(getActivity(), QuWeiHanZiActivity.class);
                startActivity(intent12);
                break;
        }
    }
}
