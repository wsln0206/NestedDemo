package com.wsln.mydemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wsln.mydemo.R;

import java.util.List;

/**
 * auth: liunan
 * date: 2018/7/27
 * desc:
 */
public class LendRecordAdapter extends BaseAdapter {

    private List<String> list;
    private Context mContext;

    public LendRecordAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lend_record, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItem.setText(list.get(i));
        return convertView;
    }

    class ViewHolder{
        TextView tvItem;

        public ViewHolder(View view) {
            this.tvItem = (TextView) view.findViewById(R.id.tv_item);
        }
    }
}
