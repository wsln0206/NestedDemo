package com.wsln.mydemo.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.wsln.mydemo.ui.widget.CustomViewPager;

import java.util.List;

/**
 * @auth: liunan
 * @date: 2018/7/25
 * @desc:
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> tabTitles;
    //private CustomViewPager mViewPager;

    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tabTitles) {
        super(fm);
        this.fragments = fragments;
        this.tabTitles = tabTitles;
        //this.mViewPager = viewPager;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        //mViewPager.setObjectForPosition(fragment,position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
