package com.wsln.mydemo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wsln.mydemo.R;
import com.wsln.mydemo.base.BaseFragment;
import com.wsln.mydemo.base.LazyFragment;
import com.wsln.mydemo.ui.widget.CustomScrollView;

/**
 * 标的详情
 */
public class MarkDetailsFragment extends BaseFragment {

    private ViewPager mViewPager;
    private int position;
    //private CustomScrollView scrollView;
    private int toolbarHeight;
    private int statusBarHeight;

    private SmartRefreshLayout refresh;
    public MarkDetailsFragment() {
        // Required empty public constructor
    }

    public static MarkDetailsFragment newInstance(int position, int statusBarHeight) {
        MarkDetailsFragment fragment = new MarkDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("statusBarHeight",statusBarHeight);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
            statusBarHeight = getArguments().getInt("statusBarHeight");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager = (ViewPager)getActivity().findViewById(R.id.vp_container);
        Toolbar toolbar = getActivity().findViewById(R.id.tb_titile);
        //toolbarHeight = toolbar.getHeight();
        refresh = getActivity().findViewById(R.id.refresh);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mark_details, container, false);
        initView(view);
//        mViewPager.setObjectForPosition(view,position);
        return view;
    }

//    @Override
//    public void lazyData() {
//        refresh.setEnableLoadMore(false);
//    }

    @Override
    public void onStart() {
        super.onStart();
        //initListener();
    }

//    private void initListener() {
//        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            int lastScrollY = 0;
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                int[] location = new int[2];
//                mViewPager.getLocationOnScreen(location);
//                int mVpHeight = location[1];
//                if (mVpHeight >= toolbarHeight+statusBarHeight){
//                    scrollView.setNeedScroll(false);
//                }else {
//                    scrollView.setNeedScroll(true);
//                }
//
//                lastScrollY = scrollY;
//            }
//        });
//    }

    private void initView(View view) {
        //scrollView = (CustomScrollView) view.findViewById(R.id.scroll_view);
    }


}
