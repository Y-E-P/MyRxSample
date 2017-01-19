package com.example.poudanen.myrxsample;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by poudanen on 19.01.17.
 */

public class BaseActivity extends AppCompatActivity {
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void addObserver(Disposable disposable) {
        disposables.add(disposable);
    }

    public void clear() {
        disposables.clear();
    }

    public void disposeAll() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposeAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposeAll();
    }
}
