package com.wsln.mydemo.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * auth: liunan
 * date: 2018/8/2
 * desc:
 */
public class LendRecordRvMoreAdapter extends RecyclerView.Adapter {

    private List<String> data;

    private boolean isEmpty;
    private boolean isFoot;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(null);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (isEmpty){
            return 1;
        }else if (isFoot){
            return data.size()+1;
        }else {
            return data.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setEmptyStatus(boolean isEmpty){
        this.isEmpty = isEmpty;
    }

    public void setFootStatus(boolean isFoot){
        this.isFoot = isFoot;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder{

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
