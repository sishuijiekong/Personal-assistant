package tabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Date;
import java.util.List;

import adapter.ListAdapter;
import model.Joke;
import pulltorefresh.PullListView;
import pulltorefresh.PullToRefreshLayout;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/11/01.
 * 最新笑话页面
 */
public class TabFragment1 extends Fragment implements PullToRefreshLayout.OnRefreshListener, Toolbar.OnMenuItemClickListener {

    //笑话显示列表
    private PullToRefreshLayout mRefreshLayout;
    private PullListView mPullListView;
    //笑话数据列表
    private List<Joke> jokeList= new ArrayList<>();

    private ListAdapter mAdapter;
    //网络请求
    private static OkHttpClient client=new OkHttpClient();
    //请求数据时的参数  数据页数
    private int page=1;
    //请求数据时的参数  时间戳
    private String date;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment1, container, false);
        mRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.pullToRefreshLayout);
        mPullListView = (PullListView) view.findViewById(R.id.pullListView);
        mRefreshLayout.setOnRefreshListener(this);

        //初始化数据
        initData(0);
        jokeList.add(0,new Joke());
        mAdapter = new ListAdapter(getActivity(), jokeList);
        mPullListView.setAdapter(mAdapter);
        jokeList.remove(0);
        mAdapter.notifyDataSetChanged();


        return view;
    }

    private void initData(int tag) {

        if(tag==0)
        {
            page=1;
        }else{
            page++;
        }
        date=((new Date().getTime()/100)-600)+"";
        Log.i("kkkkkkkkk",date);
        Request request = new Request.Builder()
                .url("http://japi.juhe.cn/joke/content/list.from?sort=desc&page="+page+"&pagesize=10&time="+date+"&key=91c0a24f2befe8e95096afe420df99a0")
                .build();

        Log.i("JOKE_URL","http://japi.juhe.cn/joke/content/list.from?sort=desc&page="+page+"&pagesize=10&time="+date+"&key=91c0a24f2befe8e95096afe420df99a0");
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

                        Joke joke=new Joke();
                        JSONObject json= (JSONObject) jsonArray.get(i);
                        joke.setContent(json.getString("content"));
                        joke.setUpdatetime(json.getString("updatetime"));
                        Log.i("joke",json.getString("updatetime"));
                        if(!jokeList.contains(joke))
                               jokeList.add(0,joke);

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

                initData(1);
                updateListData();

            }
        }, 2000); // 2秒后刷新
    }

    private void updateListData() {
        if (mAdapter == null) {
            mAdapter = new ListAdapter(getActivity(), jokeList);
            mPullListView.setAdapter(mAdapter);
        } else {
            mAdapter.updateListView(jokeList);
        }
    }

    //绑定ViewHolder
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        }

}




