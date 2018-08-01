package com.wsln.mydemo.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wsln.mydemo.R;
import com.wsln.mydemo.ui.adapter.MainPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BGFragment extends Fragment {

    private TabLayout tab;
    private ViewPager vpContainer;

    private List<String> tabTitles;
    private List<Fragment> fragments;

    private int statusBarHeight;

    public BGFragment() {
        // Required empty public constructor
    }

    public static BGFragment newInstance() {
        BGFragment fragment = new BGFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bg, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.tab);
        vpContainer = (ViewPager) view.findViewById(R.id.vp_container);

        tabTitles = new ArrayList<>();
        tabTitles.add("标的详情");
        tabTitles.add("借款信息");
        tabTitles.add("出借记录");

        for (int i = 0; i < 3; i++) {
            tab.addTab(tab.newTab().setText(tabTitles.get(i)));
        }

        statusBarHeight = getStatusBarHeight();

        fragments = new ArrayList<>();
//        MarkDetailsFragment markDetails = MarkDetailsFragment.newInstance(0,statusBarHeight);
//        LoanInfoFragment loanInfo = LoanInfoFragment.newInstance(1,statusBarHeight);
//        LendRecordFragment lendRecord = LendRecordFragment.newInstance(2,statusBarHeight);
//        fragments.add(markDetails);
//        fragments.add(loanInfo);
//        fragments.add(lendRecord);
        for (int i=0;i<3;i++){
            OneFragment oneFragment = OneFragment.newInstance();
            fragments.add(oneFragment);
        }

        MainPagerAdapter adapter = new MainPagerAdapter(getChildFragmentManager(), fragments, tabTitles);
        vpContainer.setAdapter(adapter);
        tab.setupWithViewPager(vpContainer);
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
