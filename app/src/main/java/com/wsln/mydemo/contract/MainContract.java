package com.wsln.mydemo.contract;

import com.wsln.mydemo.base.BasePresenter;
import com.wsln.mydemo.base.BaseView;

/**
 * @auth: liunan
 * @date: 2018/7/25
 * @desc:
 */
public interface MainContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter>{

    }
}
