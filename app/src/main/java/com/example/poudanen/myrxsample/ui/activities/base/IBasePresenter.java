package com.example.poudanen.myrxsample.ui.activities.base;

/**
 * Created by poudanen on 07.02.17.
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V mvpView);

    void detachView();
}
