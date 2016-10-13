package com.medical.patient.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medical.patient.R;

import java.util.List;

/**
 * Created by chenjun on 2016/10/9.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
        implements View.OnClickListener {
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<String> item;
    private Context mContext;
    private int selectedPos = -1;

    public RecyclerViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.item = list;
    }


    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_signaltext_item,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,  int position) {
        viewHolder.mTextView.setText(item.get(position));
        //将数据保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);

        if (selectedPos == position) {
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_300));
        } else {
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_20));
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_300));
            selectedPos = (int) v.getTag();
            // 注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
            // 一定要刷新 不然没有选中高亮的效果
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    //获取数据的数量
    @Override
    public int getItemCount() {
        return item.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.item);
        }
    }
}
