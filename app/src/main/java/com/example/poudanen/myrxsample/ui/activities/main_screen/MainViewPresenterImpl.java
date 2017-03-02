package com.example.poudanen.myrxsample.ui.activities.main_screen;

import com.example.poudanen.myrxsample.data.DataManager;
import com.example.poudanen.myrxsample.data.model.User;
import com.example.poudanen.myrxsample.data.model.UserCredentials;
import com.example.poudanen.myrxsample.ui.activities.base.BasePresenter;
import com.example.poudanen.myrxsample.utils.Utils;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Yuriy on 31.01.2017.
 */

public class MainViewPresenterImpl<V extends MainView> extends BasePresenter<V> implements MainPresenter<V> {

    private IntercatorInterface intercatorInterface;
    private DataManager dataManager;

    @Inject
    public MainViewPresenterImpl(IntercatorInterface intercatorInterface, DataManager dataManager) {
        this.intercatorInterface = intercatorInterface;
        this.dataManager = dataManager;
    }

    @Override
    public void OnCreate() {
        getBaseView().showSomeText(dataManager.getUserCredentials().getName());
    }

    @Override
    public void OnDestroy() {

    }

    @Override
    public void OnPause() {

    }

    @Override
    public void verifyCredentials(String login, String password) {
        //intercatorInterface.saveUserCredentials(new UserCredentials(login, password));

    }

    @Override
    public void getUser() {
        Single.just(intercatorInterface.getUserCredentials()).filter(new Predicate<UserCredentials>() {
            @Override
            public boolean test(UserCredentials userCredentials) throws Exception {
                return userCredentials != null && !userCredentials.getName().isEmpty() && !userCredentials.getPassword().isEmpty();
            }
        }).subscribe(new MaybeObserver<UserCredentials>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(UserCredentials userCredentials) {
                getBaseView().showCredentials(userCredentials.getName(), userCredentials.getPassword());
            }

            @Override
            public void onError(Throwable e) {
                getBaseView().showError(e.getMessage());
                getBaseView().hideProgress();
            }

            @Override
            public void onComplete() {
                //mainView.onSuccess();
            }
        });
    }

    @Override
    public void saveData(final String login, final String password) {
        intercatorInterface.saveUserToken(Utils.base64Generator(login, password));
        getBaseView().showProgress();
        dataManager.getUser(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        getBaseView().showSomeText(user.toString());
                        intercatorInterface.saveUserCredentials(new UserCredentials(login, password));
                        getBaseView().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            try {
                                getBaseView().showError(((HttpException) e).response().errorBody().string());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        getBaseView().hideProgress();

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    }

    @Override
    public void openSecondActivity() {
        getBaseView().openSecondScreen();
    }
}
