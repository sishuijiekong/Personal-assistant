package activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.MyRecycleViewAdapter_ShowList;
import mysqlite.ChengYuDB;
import util.DividerGridItemDecoration;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/4.
 * 足迹/收藏夹 列表显示页面
 */
public class ShowListActivity extends AppCompatActivity implements View.OnClickListener {

    //列表类型（足迹/收藏夹）
    private TextView title;
    //返回按钮
    private ImageView back;
    //分享按钮
    private  ImageView share;
    //列表类型
    private String type;
    //列表
    private RecyclerView mRecyclerView;
    //数据Adapter
    private MyRecycleViewAdapter_ShowList adapter_showList;
    //数据列表
    private List<String> list=new ArrayList<>();
    //数据库实例
    private ChengYuDB mChengYuDB=new ChengYuDB(this);
    //删除提示窗
    private PopupWindow mPopupWindow;
    //数据游标
    private Cursor mCursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.showlist);
        //获取上一个Activity传输过来的列表类型的值
        final Intent intent=getIntent();
        type=intent.getStringExtra("type");

        title= (TextView) findViewById(R.id.showlist_type);
        title.setText(type);

        //获取足迹.收藏夹控件并设置点击事件监听
        back= (ImageView) findViewById(R.id.showlist_close);
        share= (ImageView) findViewById(R.id.showlist_share);
        back.setOnClickListener(this);
        share.setOnClickListener(this);

        mRecyclerView= (RecyclerView) findViewById(R.id.showlist_recyclerview);
        //获得列表数据
        initData();
        adapter_showList=new MyRecycleViewAdapter_ShowList(this,list);
        mRecyclerView.setAdapter(adapter_showList);
        //设置布局管理器 网格布局 每行两列
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        adapter_showList.setOnItemClickLitener(new MyRecycleViewAdapter_ShowList.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //单击显示提示框
                showPopupwindow(view,position);

            }

            @Override
            public void onItemLongClick(View view, int position) {

                //长按列表项，删除数据项

                if(type.equals("足迹")){
                    mChengYuDB.delete2(list.get(position));
                }else if(type.equals("收藏夹")){
                    mChengYuDB.delete3(list.get(position));
                }
                adapter_showList.removeData(position);

            }
        });



    }

    private void showPopupwindow(View view, final int position) {

        //查看详情按钮
        TextView chakanxiangqing;
        //删除数据按钮
        TextView shanchushuju;
        //获取弹窗布局
        View popupView = getLayoutInflater().inflate(R.layout.popupwindow, null);
        //获取顶层布局
        View topview = getLayoutInflater().inflate(R.layout.showlist, null);
        //创建一个弹窗并设置属性和事件监听
        mPopupWindow=new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.showAtLocation(topview, Gravity.CENTER,0,0);
        chakanxiangqing= (TextView) popupView.findViewById(R.id.popupwindow_xiangqing);
        shanchushuju= (TextView) popupView.findViewById(R.id.popupwindow_delete);
        chakanxiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //转到相应的成语的详细信息
                Intent intent5=new Intent(ShowListActivity.this,ShowChengYuActivity.class);
                intent5.putExtra("name",list.get(position).toString());
                startActivity(intent5);
                mPopupWindow.dismiss();
            }
        });
        shanchushuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除相应的数据
                if(type.equals("足迹")){
                    mChengYuDB.delete2(list.get(position));
                }else if(type.equals("收藏夹")){
                    mChengYuDB.delete3(list.get(position));
                }
                adapter_showList.removeData(position);
                mPopupWindow.dismiss();
                Toast.makeText(ShowListActivity.this,"已经清除此数据",Toast.LENGTH_SHORT).show();
            }
        });



    }

    /**
     * 从数据库中读取数据  初始化列表数据
     */
    private void initData() {
        if(type.equals("足迹")){
                mCursor=mChengYuDB.select2();
        }else if(type.equals("收藏夹")){
                 mCursor=mChengYuDB.select3();
        }
        int count=mCursor.getCount();
        if(count<=0){
            list.add("没有任何记录");
        }else{
           while (mCursor.moveToNext()){
               list.add(mCursor.getString(1));
           }
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.showlist_close:
                finish();
                break;
            case R.id.showlist_share:
                Toast.makeText(this,"分享功能暂未实现",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
