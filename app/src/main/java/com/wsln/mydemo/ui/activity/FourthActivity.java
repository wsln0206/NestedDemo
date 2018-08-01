package com.wsln.mydemo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wsln.mydemo.R;
import com.wsln.mydemo.ui.fragment.BGFragment;

public class FourthActivity extends AppCompatActivity {

    private Toolbar tbTitle;
    private FrameLayout flContainer;
    private TextView tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        initView();
        initData();
        initListener();
    }

    private void initListener() {

    }

    private void initData() {
        BGFragment bgFragment = BGFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_container,bgFragment);
        ft.commit();
    }

    private void initView() {

        tbTitle = (Toolbar) findViewById(R.id.tb_title);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);
    }


}
