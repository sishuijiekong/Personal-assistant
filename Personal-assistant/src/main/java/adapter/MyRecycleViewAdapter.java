package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.ChengYu_m;
import zxl.com.myapplication.Fragment2;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/10/20.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private Context context;
    private List<ChengYu_m> list;
    private OnItemClickLitener mOnItemClickLitener;

    public MyRecycleViewAdapter(Context context, List<ChengYu_m> list){
        this.context=context;
        this.list=list;
    }



    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflater=LayoutInflater.from(context).inflate(R.layout.fragment2_recyclerview_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(inflater);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

            Log.d("mCursor2",list.get(position).toString());
            holder.item_chengyu.setText(list.get(position).getName());
            holder.item_pingyin.setText(list.get(position).getPinyin());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(int position) {

        notifyItemInserted(position);
    }
    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView item_num;
        public TextView item_chengyu;
        public TextView item_pingyin;
        public TextView item_shoucang;
        public ViewHolder(View itemView) {
            super(itemView);
            item_num= (ImageView) itemView.findViewById(R.id.fragment2_recyclerview_item_num);
            item_chengyu= (TextView) itemView.findViewById(R.id.fragment2_recyclerview_item_chengyu);
            item_pingyin= (TextView) itemView.findViewById(R.id.fragment2_recyclerview_item_pingyin);
            item_shoucang= (TextView) itemView.findViewById(R.id.fragment2_recyclerview_item_shoucang);

        }
    }

}

