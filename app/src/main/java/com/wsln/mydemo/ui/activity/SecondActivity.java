package com.wsln.mydemo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wsln.mydemo.R;
import com.wsln.mydemo.ui.adapter.MainPagerAdapter;
import com.wsln.mydemo.ui.fragment.LendRecordFragment;
import com.wsln.mydemo.ui.fragment.LoanInfoFragment;
import com.wsln.mydemo.ui.fragment.MarkDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Toolbar tbTitile;
    private TabLayout tab;
    private ViewPager vpContainer;

    private List<String> tabTitles;
    private List<Fragment> fragments;

    private int statusBarHeight;
    private TextView tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();

        initListener();
    }

    private void initListener() {
        vpContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this,"提交数据",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        tbTitile = (Toolbar) findViewById(R.id.tb_titile);
        tab = (TabLayout) findViewById(R.id.tab);
        vpContainer = (ViewPager) findViewById(R.id.vp_container);
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);

        tabTitles = new ArrayList<>();
        tabTitles.add("标的详情");
        tabTitles.add("借款信息");
        tabTitles.add("出借记录");

        //tbTitile.setTitle("测试");
        for (int i = 0; i < 3; i++) {
            tab.addTab(tab.newTab().setText(tabTitles.get(i)));
        }

        statusBarHeight = getStatusBarHeight();

        fragments = new ArrayList<>();
        MarkDetailsFragment markDetails = MarkDetailsFragment.newInstance(0, statusBarHeight);
        LoanInfoFragment loanInfo = LoanInfoFragment.newInstance(1, statusBarHeight);
        LendRecordFragment lendRecord = LendRecordFragment.newInstance(2, statusBarHeight);
        fragments.add(markDetails);
        fragments.add(loanInfo);
        fragments.add(lendRecord);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments, tabTitles);
        vpContainer.setAdapter(adapter);
        tab.setupWithViewPager(vpContainer);

    }


    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
