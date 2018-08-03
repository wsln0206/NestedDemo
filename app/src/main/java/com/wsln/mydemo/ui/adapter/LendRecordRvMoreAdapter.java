package com.wsln.mydemo.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsln.mydemo.R;

import java.util.List;

/**
 * auth: liunan
 * date: 2018/8/2
 * desc:
 */
public class LendRecordRvMoreAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> data;

    private static final int TYPE_EMPTY = 100001;
    private static final int TYPE_ITEM = 100002;
    private static final int TYPE_FOOT = 100003;

//    private boolean isEmpty;
    private boolean isFoot;

    private OnItemClickListener itemClickListener;
    private OnLoadMoreListener loadMoreListener;
    private View emptyView;
    private View footView;
    private View itemView;

    public LendRecordRvMoreAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_EMPTY){
            emptyView = LayoutInflater.from(context).inflate(R.layout.empty_view, parent, false);
            return new EmptyViewHolder(emptyView);
        }else if (viewType == TYPE_FOOT){
            footView = LayoutInflater.from(context).inflate(R.layout.foot_view,parent,false);
            return new FootViewHolder(footView);
        }else {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_lend_record, parent, false);
            return new ItemViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmptyViewHolder){
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
        }else if (holder instanceof FootViewHolder){
            FootViewHolder footViewHolder = (FootViewHolder) holder;

        }else if (holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.tvItem.setText(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (data.isEmpty()) {
            return 1;
        }else if (loadMoreListener != null){
            return data.size()+1;
        }else {
            return data.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data.isEmpty() && position == 0){
            return TYPE_EMPTY;
        }else if (data.size() == position){
            return TYPE_FOOT;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }



    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (loadMoreListener != null &&  findScrollLastPosition(layoutManager)+1 == getItemCount()){
                        //startLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (loadMoreListener != null &&  findScrollLastPosition(layoutManager)+1 == getItemCount()){
                    if (data.isEmpty()){
                        return;
                    }
                    startLoadMore();
                }
            }
        });
    }

    /**
     * 查找滚动的最后一个位置
     * @param layoutManager
     */
    private int findScrollLastPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager llm = (LinearLayoutManager) layoutManager;
            int lastPosition = llm.findLastCompletelyVisibleItemPosition();
            return lastPosition;
        }
        return -1;
    }

    /**
     * 符合条件，开始加载更多
     */
    private void startLoadMore() {
        if (loadMoreListener != null){
            loadMoreListener.onLoadMore();
        }
    }

    public void setFootStatus(boolean isFoot){
        this.isFoot = isFoot;
    }

    public void appendData(List<String> list){
        if (!data.isEmpty() && !list.isEmpty()) {
            data.addAll(data.size(),list);
        }
        notifyItemRangeChanged(data.size()-1,list.size());
    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tvItem;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v);
                }
            });
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

    public void setOnClickListener(OnItemClickListener listener){
        this.itemClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener){
        this.loadMoreListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v);
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }
}
