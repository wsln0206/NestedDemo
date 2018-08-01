package com.wsln.mydemo.base;

/**
 * @auth: liunan
 * @date: 2018/7/24
 * @desc:
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
