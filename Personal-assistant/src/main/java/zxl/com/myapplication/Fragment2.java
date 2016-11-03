package zxl.com.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.MyRecycleViewAdapter;
import mysqlite.ChengYuDB;
import showchengyu.ShowChengYu;
import util.DividerLinearItemDecoration;

/**
 * Created by HongJay on 2016/8/11.
 */
public class Fragment2 extends Fragment {

    private EditText search;
    private TextView yixueguo;
    private TextView yishoucang;
    private RecyclerView recyclerView;
    private List<String> list=new ArrayList<>();
    private MyRecycleViewAdapter adapter;
    private ChengYuDB mChengYuDB;
    private Cursor mCursor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);

        mChengYuDB=new ChengYuDB(getContext());
        mCursor=mChengYuDB.select1();

        search= (EditText) view.findViewById(R.id.fragment2_magnify);
        yixueguo= (TextView) view.findViewById(R.id.fragment2_yixueguo);
        yishoucang= (TextView) view.findViewById(R.id.fragment2_yishoucang);
        recyclerView= (RecyclerView) view.findViewById(R.id.fragment2_recyclerview);
        for(int i=0;i<mCursor.getCount();i++){
            mCursor.moveToPosition(i);
            list.add(mCursor.getString(1).toString());
            Log.d("mCursor0",mCursor.getString(0).toString());
            Log.d("mCursor1",mCursor.getString(1).toString());
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
                Intent intent=new Intent(getActivity(), ShowChengYu.class);
                intent.putExtra("name",list.get(position).toString());
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
        return view;
    }

}
