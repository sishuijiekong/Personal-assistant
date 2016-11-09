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

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import adapter.FindViewPageAdapter;

/**
 * Created by HongJay on 2016/8/11.
 */
public class Fragment3 extends Fragment {

    private View view;
    private ViewPager mViewPager;
    public ImageHandler handler = new ImageHandler(new WeakReference<Fragment3>(this));
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment3, container, false);
        mViewPager= (ViewPager) view.findViewById(R.id.fragment3_guanggao);

        initViewPager();
        return view;
    }

    private void initViewPager() {



        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view1 =inflater.inflate(R.layout.viewpager_item, null);
        View view2 =inflater.inflate(R.layout.viewpager_item, null);
        View view3 =inflater.inflate(R.layout.viewpager_item, null);
        View view4 =inflater.inflate(R.layout.viewpager_item, null);

        ImageView img1 = (ImageView) view1.findViewById(R.id.viewpager_item_pic);
        ImageView img2 = (ImageView) view2.findViewById(R.id.viewpager_item_pic);
        ImageView img3 = (ImageView) view3.findViewById(R.id.viewpager_item_pic);
        ImageView img4 = (ImageView) view3.findViewById(R.id.viewpager_item_pic);
        img1.setImageResource(R.mipmap.guanggao1);
        img2.setImageResource(R.mipmap.guanggao2);
        img3.setImageResource(R.mipmap.guanggao3);
        img4.setImageResource(R.mipmap.guanggao4);

        ArrayList<ImageView> views = new ArrayList<ImageView>();
        views.add(img1);
        views.add(img2);
        views.add(img3);
        mViewPager.setAdapter(new FindViewPageAdapter(views));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //配合Adapter的currentItem字段进行设置。
            @Override
            public void onPageSelected(int arg0) {
                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, arg0, 0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            //覆写该方法实现轮播效果的暂停和恢复
            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
        mViewPager.setCurrentItem(Integer.MAX_VALUE/2);//默认在中间，使用户看不到边界
        //开始轮播效果
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);

    }
    class ImageHandler extends Handler {

        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE  = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT   = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT  = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED  = 4;

        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private WeakReference<Fragment3> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<Fragment3> wk){
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Fragment3 activity = weakReference.get();
            if (activity == null) {
                //Activity已经回收，无需再处理UI了
                return;
            }
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                activity.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    activity.mViewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }


}
