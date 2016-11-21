package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import model.Joke;
import zxl.com.myapplication.R;

/**
 * ListAdapter
 * Created by张显林.
 */
public class ListAdapter extends BaseListAdapter<Joke> implements View.OnClickListener {

    private List<Joke> list;
    private Context context;
    public ListAdapter(Context context, List<Joke> objects) {
        super(context,objects);
        this.list=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            if (getItemViewType(position) == 0) {
                convertView = ((Activity) (mContext)).getLayoutInflater().inflate(R.layout.tab_fragment1_has_no_data, parent, false);
                holder.noDataRootLayout = (LinearLayout) convertView.findViewById(R.id.root_layout);
                holder.noDataRootLayout.setOnClickListener(this);
            } else {
                convertView = ((Activity) (mContext)).getLayoutInflater().inflate(R.layout.item_jokelist_view, parent, false);
                holder.textView1 = (TextView) convertView.findViewById(R.id.item_list_view_text);
                holder.textView2 = (TextView) convertView.findViewById(R.id.item_list_view_time);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (hasNoData) {
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(getScreenWidth(), getScreenHeight() * 2 / 3);
            holder.noDataRootLayout.setLayoutParams(lp);
        } else {
            holder.textView1.setText(list.get(position).getContent());
            holder.textView2.setText(list.get(position).getUpdatetime());
        }

        return convertView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.root_layout:
                list.remove(0);
                break;
        }

    }

    private static final class ViewHolder {
        TextView textView1;
        TextView textView2;

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
