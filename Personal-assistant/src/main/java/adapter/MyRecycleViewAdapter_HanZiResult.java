package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.ShowHanZiActivity;
import model.ChengYu_m;
import model.HanZi;
import util.DividerGridItemDecoration;
import zxl.com.myapplication.R;

/**
 * Created by 张显林 on 2016/10/20.
 */
public class MyRecycleViewAdapter_HanZiResult extends RecyclerView.Adapter<MyRecycleViewAdapter_HanZiResult.ViewHolder> {

    private Context context;
    private List<HanZi> list;
    private List<HanZi> list2;
    private OnItemClickLitener mOnItemClickLitener;
    private int [] bihua=new int[50];

    public MyRecycleViewAdapter_HanZiResult(Context context){
        this.context=context;

    }
    public void setList(List<HanZi> list2){

        this.list2=list2;
    }
    public List<HanZi>  getList(){

        return list2;
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

        View inflater=LayoutInflater.from(context).inflate(R.layout.item_hanziresult_list_view,parent,false);
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


        holder.item_title.setText("拼音为 "+getList().get(0).getPy()+" 的汉字");

        MyRecycleViewAdapter_HanZiResult_Item adapter=new MyRecycleViewAdapter_HanZiResult_Item(context,getList());
        holder.mRecyclerView.setAdapter(adapter);
        holder.mRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
        holder.mRecyclerView.addItemDecoration(new DividerGridItemDecoration(context));
        adapter.setOnItemClickLitener(new MyRecycleViewAdapter_HanZiResult_Item.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, ShowHanZiActivity.class);
                intent.putExtra("name",getList().get(position).getZi());
                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return 1;
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
        public RecyclerView mRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            item_title= (TextView) itemView.findViewById(R.id.item_hanziresult_view_title);
            mRecyclerView= (RecyclerView) itemView.findViewById(R.id.item_hanziresult_view_recyclerview);
        }
    }

}

