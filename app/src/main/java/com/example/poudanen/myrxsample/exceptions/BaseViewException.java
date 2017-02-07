package com.example.poudanen.myrxsample.exceptions;

/**
 * Created by poudanen on 07.02.17.
 */

public class BaseViewException extends RuntimeException {

    public BaseViewException() {
        super("Please call Presenter.attachView(IBaseView) before" +
                " requesting data to the Presenter");
    }

}
