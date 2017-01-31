package com.example.poudanen.myrxsample.activities.presenter;

import com.example.poudanen.myrxsample.activities.interactor.IntercatorInterface;
import com.example.poudanen.myrxsample.activities.view.MainView;
import com.example.poudanen.myrxsample.model.UserCredentials;

import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * Created by Yuriy on 31.01.2017.
 */

public class MainViewPresenterImpl implements MainPresenter {

    private MainView mainView;
    private IntercatorInterface intercatorInterface;

    public MainViewPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    public MainViewPresenterImpl(MainView mainView, IntercatorInterface intercatorInterface) {
        this.mainView = mainView;
        this.intercatorInterface = intercatorInterface;
    }

    @Override
    public void OnCreate() {

    }

    @Override
    public void OnDestroy() {

    }

    @Override
    public void OnPause() {

    }

    @Override
    public void verifyCredentials(String login, String password) {
        intercatorInterface.saveUserCredentials(new UserCredentials(login, password));
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
                mainView.showCredentials(userCredentials.getName(), userCredentials.getPassword());
            }

            @Override
            public void onError(Throwable e) {
                mainView.showError(e.getMessage());
            }

            @Override
            public void onComplete() {
                //mainView.onSuccess();
            }
        });
    }

    @Override
    public void saveData(String login, String password) {
        Single.just(intercatorInterface.saveUserCredentials(new UserCredentials(login, password))).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    getUser();
                } else {
                    onError(new Throwable("Error"));
                }

            }

            @Override
            public void onError(Throwable e) {
                mainView.showError(e.getMessage());
            }
        });
    }
}
