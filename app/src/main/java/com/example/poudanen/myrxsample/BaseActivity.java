package com.example.poudanen.myrxsample;

import android.support.v7.app.AppCompatActivity;

import com.example.poudanen.myrxsample.di_sample.components.ActivityComponent;
import com.example.poudanen.myrxsample.di_sample.components.DaggerActivityComponent;
import com.example.poudanen.myrxsample.di_sample.modules.ActivityModule;

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

    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(BaseApplications.get(this).getAppComponent())
                    .build();
        }
        return activityComponent;
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
