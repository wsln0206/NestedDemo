package com.wsln.mydemo.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wsln.mydemo.R;
import com.wsln.mydemo.ui.adapter.MainPagerAdapter;
import com.wsln.mydemo.ui.fragment.LendRecordFragment;
import com.wsln.mydemo.ui.fragment.LoanInfoFragment;
import com.wsln.mydemo.ui.fragment.MarkDetailsFragment;
import com.wsln.mydemo.utils.ScreenUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    private List<String> tabTitles;
    private List<Fragment> fragments;

    int toolBarPositionY = 0;
    private int statusBarHeight;
    private Toolbar tbTitile;
    private TabLayout tab;
    private ViewPager vpContainer;
    private AppBarLayout appBarLayout;
    private SmartRefreshLayout refresh;

    private final MyHandler myHandler = new MyHandler(this);

    private static class MyHandler extends Handler{

        WeakReference<ThirdActivity> reference;

        private MyHandler(ThirdActivity activity) {
            this.reference = new WeakReference<ThirdActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ThirdActivity activity = reference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        activity.setRefreshStatus(false);
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initView();

        initListener();
    }

    private void initView() {
        //tbTitile = (Toolbar) findViewById(R.id.tb_titile);
        tab = (TabLayout) findViewById(R.id.tab);
        vpContainer = (ViewPager) findViewById(R.id.vp_container);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        refresh = (SmartRefreshLayout)findViewById(R.id.refresh);

        tabTitles = new ArrayList<>();
        tabTitles.add("标的详情");
        tabTitles.add("借款信息");
        tabTitles.add("出借记录");

        //        tbTitile.setTitle("测试");
        for (int i = 0; i < 3; i++) {
            tab.addTab(tab.newTab().setText(tabTitles.get(i)));
        }

        statusBarHeight = getStatusBarHeight();
        //
        //        tbTitile.post(new Runnable() {
        //            @Override
        //            public void run() {
        //                dealWithViewPager();
        //            }
        //        });

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

    private void initListener() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (verticalOffset >=0) {
//                    refresh.setEnabled(true);
//                }else {
//                    refresh.setEnabled(false);
//                }
            }
        });
//        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        myHandler.sendEmptyMessageDelayed(1,1000);
//                    }
//                }).run();
//            }
//        });

        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1500);
            }
        });

        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1500);
            }
        });
    }

    private void dealWithViewPager() {
        toolBarPositionY = tbTitile.getHeight();
        ViewGroup.LayoutParams params = vpContainer.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getApplicationContext()) - toolBarPositionY - tab.getHeight() - statusBarHeight;
        vpContainer.setLayoutParams(params);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void setRefreshStatus(boolean isRefresh){
        refresh.finishRefresh(1500);
    }
}
