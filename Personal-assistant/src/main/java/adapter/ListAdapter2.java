package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;
import java.util.List;
import model.Joke;
import model.New;
import zxl.com.myapplication.R;

/**
 * ListAdapter
 * Created by XiaoWei on 2015-11-14.
 */
public class ListAdapter2 extends BaseListAdapter<New> {

    private List<New> list;
    private Context context;
    private Picasso picasso;
    private OkHttpClient client;


    public ListAdapter2(Context context, List<New> objects) {
        super(context,objects);
        this.list=objects;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            if (getItemViewType(position) == 0) {
                convertView = ((Activity) (mContext)).getLayoutInflater().inflate(R.layout.tab_fragment1_has_no_data, parent, false);
                holder.noDataRootLayout = (LinearLayout) convertView.findViewById(R.id.root_layout);
            } else {
                convertView = ((Activity) (mContext)).getLayoutInflater().inflate(R.layout.item_list_view2, parent, false);
                holder.title= (TextView) convertView.findViewById(R.id.item_list_view2_title);
                holder.pic1= (ImageView) convertView.findViewById(R.id.item_list_view2_pic1);
                holder.author= (TextView) convertView.findViewById(R.id.item_list_view2_author);
                holder.time= (TextView) convertView.findViewById(R.id.item_list_view2_time);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();


        }

        if (hasNoData) {
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(getScreenWidth(), getScreenHeight() * 2 / 3);
            holder.noDataRootLayout.setLayoutParams(lp);
        } else {
            holder.title.setText(list.get(position).getTitle());
            holder.author.setText(list.get(position).getAuthor_name());
            holder.time.setText(list.get(position).getDate());
            Picasso.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder.pic1);

    }

        return convertView;
    }

    private static final class ViewHolder {
        TextView title;
        ImageView pic1;
        TextView author;
        TextView time;

        LinearLayout noDataRootLayout;
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetric = Resources.getSystem().getDisplayMetrics();
        return displayMetric.widthPixels;
    }

    private int getScreenHeight() {
        DisplayMetrics displayMetric = Resources.getSystem().getDisplayMetrics();
        return displayMetric.heightPixels;
    }

}
