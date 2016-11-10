package zxl.com.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.FindViewPageAdapter;

/**
 * Created by HongJay on 2016/8/11.
 */
public class Fragment3 extends Fragment {

    private View view;
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment3, container, false);
        textView= (TextView) view.findViewById(R.id.textView);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        textView.setText(sdf.format(new Date()));
        return view;
    }
}
