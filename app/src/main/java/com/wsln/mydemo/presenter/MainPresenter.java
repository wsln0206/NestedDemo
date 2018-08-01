package com.wsln.mydemo.presenter;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import com.wsln.mydemo.base.BaseView;
import com.wsln.mydemo.contract.MainContract;
import com.wsln.mydemo.ui.fragment.LendRecordFragment;
import com.wsln.mydemo.ui.fragment.LoanInfoFragment;
import com.wsln.mydemo.ui.fragment.MarkDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * @auth: liunan
 * @date: 2018/7/25
 * @desc:
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;


    @SuppressLint("RestrictedApi")
    public MainPresenter(MainContract.View view) {
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
