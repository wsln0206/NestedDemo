package com.wsln.mydemo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.wsln.mydemo.R;
import com.wsln.mydemo.base.BaseActivity;
import com.wsln.mydemo.contract.MainContract;
import com.wsln.mydemo.presenter.MainPresenter;
import com.wsln.mydemo.ui.adapter.MainPagerAdapter;
import com.wsln.mydemo.ui.fragment.LendRecordFragment;
import com.wsln.mydemo.ui.fragment.LoanInfoFragment;
import com.wsln.mydemo.ui.fragment.MarkDetailsFragment;
import com.wsln.mydemo.ui.widget.CustomScrollView;
import com.wsln.mydemo.ui.widget.MyScrollView;
import com.wsln.mydemo.ui.widget.MyViewPager;
import com.wsln.mydemo.utils.DensityUtil;
import com.wsln.mydemo.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MainActivity extends BaseActivity implements MainContract.View {
    private MainContract.Presenter mPresenter;
    private Toolbar mTbTitile;
    private TabLayout mTab;
    private ViewPager mVpContainer;
    private TabLayout mTabTop;
    private CustomScrollView mScrollView;

    private List<Fragment> fragments;
    private List<String> tabTitles;

    int toolBarPositionY = 0;
    private int statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mVpContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //mVpContainer.resetHeight(i);
                mTabTop.setScrollPosition(i,0,true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mTabTop.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTab.setScrollPosition(tab.getPosition(),0,true);
                mVpContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void initData() {
        mPresenter.start();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @SuppressLint("NewApi")
    private void initView() {
        mTbTitile = (Toolbar) findViewById(R.id.tb_titile);
        mTab = (TabLayout) findViewById(R.id.tab);
        mVpContainer = (ViewPager) findViewById(R.id.vp_container);
        mTabTop = (TabLayout) findViewById(R.id.tab_top);
        mScrollView = (CustomScrollView) findViewById(R.id.scroll_view);

        tabTitles = new ArrayList<>();
        tabTitles.add("标的详情");
        tabTitles.add("借款信息");
        tabTitles.add("出借记录");

        mTbTitile.setTitle("测试");
        for (int i = 0; i < 3; i++) {
            mTab.addTab(mTab.newTab().setText(tabTitles.get(i)));
        }
        for (int i = 0; i < 3; i++) {
            mTabTop.addTab(mTabTop.newTab().setText(tabTitles.get(i)));
        }
        //mTabTop.setVisibility(View.VISIBLE);

        statusBarHeight = getStatusBarHeight();

        fragments = new ArrayList<>();
        MarkDetailsFragment markDetails = MarkDetailsFragment.newInstance(0,statusBarHeight);
        LoanInfoFragment loanInfo = LoanInfoFragment.newInstance(1,statusBarHeight);
        LendRecordFragment lendRecord = LendRecordFragment.newInstance(2,statusBarHeight);
        fragments.add(markDetails);
        fragments.add(loanInfo);
        fragments.add(lendRecord);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments, tabTitles);
        mVpContainer.setAdapter(adapter);
        mTab.setupWithViewPager(mVpContainer);
        //mVpContainer.resetHeight(0);
        mTbTitile.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });

        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(170);

            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                mTab.getLocationOnScreen(location);
                int mTabHeight = location[1];
                if (mTabHeight <= toolBarPositionY+statusBarHeight){
                    mTabTop.setVisibility(View.VISIBLE);
                    mScrollView.setNeedScroll(false);
                }else {
                    mTabTop.setVisibility(View.GONE);
                    mScrollView.setNeedScroll(true);
                }

                lastScrollY = scrollY;
            }
        });


    }


    private void dealWithViewPager() {
        toolBarPositionY = mTbTitile.getHeight();
        ViewGroup.LayoutParams params = mVpContainer.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getApplicationContext()) - toolBarPositionY - mTab.getHeight()- statusBarHeight+1;
        mVpContainer.setLayoutParams(params);
    }

    private int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
        if(resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
