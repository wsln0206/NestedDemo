package com.wsln.mydemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wsln.mydemo.R;

import java.util.List;

/**
 * auth: liunan
 * date: 2018/7/27
 * desc:
 */
public class LendRecordRvAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> list;

    private final int EMPTY_VIEW = 0;
    private final int ITEM_VIEW = 1;
    private final int FOOT_VIEW = 2;

    private onItemClickListener listener;

    public LendRecordRvAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == EMPTY_VIEW){
            View view = LayoutInflater.from(context).inflate(R.layout.empty_view, viewGroup, false);
            return new EmptyViewHolder(view);
        }else if (viewType == ITEM_VIEW){
            View view = LayoutInflater.from(context).inflate(R.layout.item_lend_record, viewGroup, false);
            return new MyViewHolder(view);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof EmptyViewHolder){
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) viewHolder;
            return;
        }else if (viewHolder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.tvItem.setText(list.get(position));
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size()>0){
            return list.size();
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (list != null && list.size() > 0){
            return ITEM_VIEW;
        }
        return EMPTY_VIEW;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivNoData;
        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNoData = itemView.findViewById(R.id.iv_no_data);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar pbFoot;
        public FootViewHolder(View itemView) {
            super(itemView);
            pbFoot = itemView.findViewById(R.id.pb_foot);
        }
    }

    public void setOnClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    public interface onItemClickListener{
        void onItemClick();
    }
}
