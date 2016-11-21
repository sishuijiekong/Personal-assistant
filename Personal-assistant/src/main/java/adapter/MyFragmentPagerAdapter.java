package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
/**
 * Created by 张显林 on 2016/10/11.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
    private String[] mTitles = new String[]{"Tab 1", "Tab 2", "Tab 3","Tab 4","Tab 5"};
    private List<Fragment> fragments;
    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
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
