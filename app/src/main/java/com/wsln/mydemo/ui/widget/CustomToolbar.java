package com.wsln.mydemo.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wsln.mydemo.R;

/**
 * auth: liunan
 * date: 2018/7/31
 * desc:
 */
public class CustomToolbar extends Toolbar {
    private RelativeLayout rlBack;
    private ImageView ivBack;
    private TextView tvTitle;
    private RelativeLayout rlDoThing;
    private ImageView ivDoThing;

    public CustomToolbar(Context context) {
        super(context);
        initView(context);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_view, null, false);

        rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rlDoThing = (RelativeLayout) findViewById(R.id.rl_do_thing);
        ivDoThing = (ImageView) findViewById(R.id.iv_do_thing);

        //然后使用LayoutParams把控件添加到子view中
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
        addView(view, lp);
        setContentInsetsRelative(10, 10);
    }

    public void setOnLeftClickListener(OnClickListener listener){
        rlBack.setOnClickListener(listener);
    }

    public void setOnRightClickListener(OnClickListener listener){
        rlDoThing.setOnClickListener(listener);
    }

    public void setMyTitle(String title){
        tvTitle.setText(title);
    }
}
