package com.wsln.mydemo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.wsln.mydemo.R;
import com.wsln.mydemo.base.LazyFragment;
import com.wsln.mydemo.ui.adapter.LendRecordAdapter;
import com.wsln.mydemo.ui.adapter.LendRecordRvAdapter;
import com.wsln.mydemo.ui.widget.CustomListView;

import java.util.ArrayList;
import java.util.List;

/**
 * auth: liunan
 * date: 2018/7/26
 * desc: 借款记录
 */
public class LendRecordFragment extends LazyFragment {
    private ViewPager mViewPager;
    private int position;
    //private ListView mLvContainer;
    private ImageView mIvNoData;
    private RecyclerView rvContainer;

    private SmartRefreshLayout refresh;

    private List<String> list;
    private LendRecordAdapter adapter;
    private LendRecordRvAdapter adapter1;

    public LendRecordFragment() {
        // Required empty public constructor
    }

    public static LendRecordFragment newInstance(int position, int statusBarHeight) {
        LendRecordFragment fragment = new LendRecordFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
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
        //refresh = (SmartRefreshLayout) getActivity().findViewById(R.id.refresh);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_lend_record, container, false);
//        initView(view);
//        //mViewPager.setObjectForPosition(view,position);
//        initData();
//        return view;
//    }


    private void initData() {
        list = new ArrayList<>();
        for (int i=0;i<40;i++){
            list.add("数据-------"+i);
        }


        //adapter = new LendRecordAdapter(list,getActivity());
        //mLvContainer.setAdapter(adapter);

        //View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, (ViewGroup) mLvContainer.getParent(), false);
        //mLvContainer.setEmptyView(mIvNoData);


//        mLvContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                list.clear();
//                adapter.notifyDataSetChanged();
//            }
//        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvContainer.setLayoutManager(layoutManager);
        adapter1 = new LendRecordRvAdapter(getActivity(),list);
        rvContainer.setAdapter(adapter1);
        adapter1.setOnClickListener(new LendRecordRvAdapter.onItemClickListener() {
            @Override
            public void onItemClick() {
                list.clear();
                adapter1.notifyDataSetChanged();
            }
        });

    }

    private void initView(View view) {
        //mLvContainer = (ListView) view.findViewById(R.id.lv_container);
        mIvNoData = (ImageView) view.findViewById(R.id.iv_no_data);
        rvContainer = (RecyclerView) view.findViewById(R.id.rv_container);
    }

    @Override
    public void onStart() {
        super.onStart();
        initListener();
    }

    private void initListener() {
//        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.finishLoadMore(1500);
//            }
//        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_lend_record;
    }

    @Override
    protected void initView() {
        initView(rootView);
        refresh = getActivity().findViewById(R.id.refresh);
        refresh.setEnableLoadMore(true);
        initData();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible){
            refresh.setEnableLoadMore(true);
        }
    }
}
