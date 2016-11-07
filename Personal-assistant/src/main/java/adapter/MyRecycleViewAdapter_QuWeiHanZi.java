package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import model.HanZi;
import model.QuWeiHanZi;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/10/20.
 */
public class MyRecycleViewAdapter_QuWeiHanZi extends RecyclerView.Adapter<MyRecycleViewAdapter_QuWeiHanZi.ViewHolder> {

    private Context context;
    private List<QuWeiHanZi> list;
    private OnItemClickLitener mOnItemClickLitener;
    private  String type;


    public MyRecycleViewAdapter_QuWeiHanZi(Context context, List<QuWeiHanZi> list,String type){
        this.context=context;
        this.list=list;
        this.type=type;
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

        View inflater=LayoutInflater.from(context).inflate(R.layout.item_item_hanziresult_list_view,parent,false);
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

        if (type.equals("0")){
            holder.item_title.setText(list.get(position).getBihua()+"画");
        }else {
            holder.item_title.setText(list.get(position).getPinyin());
        }

           holder.item_hanzi.setText(list.get(position).getZi());



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

        public TextView item_title;
        public TextView item_hanzi;
        public ViewHolder(View itemView) {
            super(itemView);
            item_title= (TextView) itemView.findViewById(R.id.item_item_hanziresult_view_title);
            item_hanzi= (TextView) itemView.findViewById(R.id.item_item_hanziresult_view_hanzi);
        }
    }

}

