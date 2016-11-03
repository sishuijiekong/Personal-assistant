package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;


/**
 * Created by HongJay on 2016/8/11.
 */
public class MyTabFragmentPagerAdapter extends FragmentPagerAdapter {
//    private String[] mTitles = new String[]{"推荐", "圆桌", "热门","收藏"};
private String[] mTitles = new String[]{"最新笑话", "今日头条"};
    private List<Fragment> fragments;

    public MyTabFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //用来设置tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


}
