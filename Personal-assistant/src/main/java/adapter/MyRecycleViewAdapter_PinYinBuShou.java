package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/10/20.
 */
public class MyRecycleViewAdapter_PinYinBuShou extends RecyclerView.Adapter<MyRecycleViewAdapter_PinYinBuShou.ViewHolder> {

    private Context context;
    private List<String> list;
    private OnItemClickLitener mOnItemClickLitener;

    public MyRecycleViewAdapter_PinYinBuShou(Context context, List<String> list){
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

        View inflater=LayoutInflater.from(context).inflate(R.layout.item_pinyinbushou_view,parent,false);
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
            holder.item_name.setText(list.get(position).toString());


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

        public TextView item_name;
        public ViewHolder(View itemView) {
            super(itemView);
            item_name= (TextView) itemView.findViewById(R.id.item_pinyinbushou_view_name);
        }
    }

}

