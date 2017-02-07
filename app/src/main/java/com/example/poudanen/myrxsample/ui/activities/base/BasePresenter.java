package com.example.poudanen.myrxsample.ui.activities.base;

import com.example.poudanen.myrxsample.exceptions.BaseViewException;

/**
 * Created by poudanen on 07.02.17.
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private V baseView;

    @Override
    public void attachView(V mvpView) {
        this.baseView = mvpView;
    }

    @Override
    public void detachView() {
        baseView = null;
    }

    public boolean isViewAttached() {
        return baseView != null;
    }

    public V getBaseView() {
        return baseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new BaseViewException();
    }
}
