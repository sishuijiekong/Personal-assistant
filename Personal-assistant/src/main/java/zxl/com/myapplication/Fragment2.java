package zxl.com.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.ShowShouCangZuJiListActivity;
import adapter.MyRecycleViewAdapter;
import model.ChengYu_m;
import mysqlite.MYDB;
import activity.SearchChengYuActivity;
import activity.ShowChengYuActivity;
import util.DividerLinearItemDecoration;

/**
 * Created by 张显林 on 2016/8/11.
 * 成语功能主界面
 */
public class Fragment2 extends Fragment implements View.OnClickListener {

    //搜索框
    private EditText search;
    //足迹
    private TextView zuji;
    //收藏夹
    private TextView shoucangjia;
    //每日推荐列表
    private RecyclerView recyclerView;
    //成语列表数据列表
    private List<ChengYu_m> list=new ArrayList<>();
    //成语列表的Adapter
    private MyRecycleViewAdapter adapter;
    //数据库实例
    private MYDB mMYDB;
    //数据库数据返回结果游标
    private Cursor mCursor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);

        mMYDB =new MYDB(getContext());
        mCursor= mMYDB.select1();

        search= (EditText) view.findViewById(R.id.fragment2_magnify);
        zuji= (TextView) view.findViewById(R.id.fragment2_yixueguo);
        shoucangjia= (TextView) view.findViewById(R.id.fragment2_yishoucang);
        zuji.setOnClickListener(this);
        shoucangjia.setOnClickListener(this);


        recyclerView= (RecyclerView) view.findViewById(R.id.fragment2_recyclerview);
        for(int i=0;i<mCursor.getCount();i++){
            mCursor.moveToPosition(i);
            list.add(new ChengYu_m(mCursor.getString(1).toString(),mCursor.getString(2).toString()));
        }

        adapter=new MyRecycleViewAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerLinearItemDecoration(getActivity(), DividerLinearItemDecoration.VERTICAL_LIST));
        /**
         * 设置item点击事件
         */
        adapter.setOnItemClickLitener(new MyRecycleViewAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " click",
                        Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ShowChengYuActivity.class);
                intent.putExtra("name",list.get(position).getName());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " long click",
                        Toast.LENGTH_SHORT).show();
                adapter.removeData(position);
            }
        });

        search.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment2_magnify://搜索按钮
                Intent intent=new Intent(getActivity(), SearchChengYuActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment2_yishoucang://收藏夹按钮
                Intent intent1=new Intent(getActivity(), ShowShouCangZuJiListActivity.class);
                intent1.putExtra("type","收藏夹");
                startActivity(intent1);
                break;
            case  R.id.fragment2_yixueguo://学习足迹按钮
                Intent intent2=new Intent(getActivity(), ShowShouCangZuJiListActivity.class);
                intent2.putExtra("type","足迹");
                startActivity(intent2);
                break;
        }
    }
}
