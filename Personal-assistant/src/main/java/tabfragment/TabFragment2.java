package tabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adapter.ListAdapter2;
import model.New;
import pulltorefresh.PullListView;
import pulltorefresh.PullToRefreshLayout;
import activity.ShowNewsActivity;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/10/11.
 */
public class TabFragment2 extends Fragment implements PullToRefreshLayout.OnRefreshListener, Toolbar.OnMenuItemClickListener{

    private PullToRefreshLayout mRefreshLayout;
    private PullListView mPullListView;
    private List<New> newList= new ArrayList<>();
    private ListAdapter2 mAdapter;
    private static OkHttpClient client=new OkHttpClient();
    private int page=1;
    private long date;
    private Random random=new Random();
    private String [] typelist={"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_fragment2, container, false);
        mRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.pullToRefreshLayout2);
        mPullListView = (PullListView) view.findViewById(R.id.pullListView2);
        mRefreshLayout.setOnRefreshListener(this);
        mPullListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"点击的是++"+i,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ShowNewsActivity.class);
                intent.putExtra("url",newList.get(i).getUrl());
                startActivity(intent);
            }
        });
        mPullListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"长按的是++"+i,Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        newList.add(new New());

        initData("top");
        mAdapter = new ListAdapter2(getActivity(), newList);
        newList.remove(0);
        mPullListView.setAdapter(mAdapter);
        return view;
    }

    /**
     * 获取新闻列表
     * @param type
     */
    private void initData(String type) {

        Request request = new Request.Builder()
                .url("http://v.juhe.cn/toutiao/index?type="+type+"&key=3d2c88292949f04d3acf4c4aa08b43b5")
                .build();

        Log.i("url++++++","http://v.juhe.cn/toutiao/index?type="+type+"&key=3d2c88292949f04d3acf4c4aa08b43b5");
        client.newCall(request).enqueue(new Callback() {

            JSONObject jsonobject;
            JSONObject jsonobject2;
            JSONArray jsonArray;
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String s = response.body().string();
                try {
                    jsonobject=new JSONObject(s);
                    jsonobject2=new  JSONObject(jsonobject.getString("result"));
                    jsonArray=jsonobject2.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++) {

                        New news=new New();
                        JSONObject json= (JSONObject) jsonArray.get(i);
                        Log.i("tag",json.toString());
                        news.setTitle(json.getString("title"));
                        news.setDate(json.getString("date"));
                        news.setAuthor_name(json.getString("author_name"));
                        news.setThumbnail_pic_s(json.getString("thumbnail_pic_s"));
                        news.setUrl(json.getString("url"));
                        newList.add(news);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {

        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.refreshFinish(true);
                Toast.makeText(getActivity(),"已经是最新数据了",Toast.LENGTH_SHORT).show();
                updateListData();
            }
        }, 2000); // 2秒后刷新
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.loadMoreFinish(true);

                initData(typelist[1+random.nextInt(10)]);
                updateListData();

            }
        }, 2000); // 2秒后刷新

    }

    private void updateListData() {
        if (mAdapter == null) {
            mAdapter = new ListAdapter2(getActivity(), newList);
            mPullListView.setAdapter(mAdapter);
        } else {
            mAdapter.updateListView(newList);
        }
    }
}
