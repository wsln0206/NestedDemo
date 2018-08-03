package com.wsln.mydemo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wsln.mydemo.R;
import com.wsln.mydemo.base.BaseFragment;
import com.wsln.mydemo.base.LazyFragment;
import com.wsln.mydemo.ui.widget.CustomViewPager;

/**
 * 借款信息
 */
public class LoanInfoFragment extends BaseFragment {
    private ViewPager mViewPager;
    private int position;
    private SmartRefreshLayout refresh;
    public LoanInfoFragment() {
        // Required empty public constructor
    }

    public static LoanInfoFragment newInstance(int position, int statusBarHeight) {
        LoanInfoFragment fragment = new LoanInfoFragment();
        Bundle args = new Bundle();
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewPager = (ViewPager)getActivity().findViewById(R.id.vp_container);
        refresh = (SmartRefreshLayout) getActivity().findViewById(R.id.refresh);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan_info, container, false);
        //mViewPager.setObjectForPosition(view,position);
        return view;
    }

//    @Override
//    public void lazyData() {
//        refresh.setEnableLoadMore(false);
//    }
}
