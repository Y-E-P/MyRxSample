package com.example.poudanen.myrxsample.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.poudanen.myrxsample.BaseActivity;
import com.example.poudanen.myrxsample.R;
import com.example.poudanen.myrxsample.activities.interactor.InteractorImpl;
import com.example.poudanen.myrxsample.activities.presenter.MainPresenter;
import com.example.poudanen.myrxsample.activities.presenter.MainViewPresenterImpl;
import com.example.poudanen.myrxsample.activities.view.MainView;
import com.example.poudanen.myrxsample.databinding.ActivityMainBinding;
import com.example.poudanen.myrxsample.databinding.ContentMainBinding;
import com.example.poudanen.myrxsample.ui.RxHelperView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mainBinding;
    private ContentMainBinding contentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        contentBinding = mainBinding.contentMain;
        setSupportActionBar(mainBinding.toolbar);
        observ();
        Disposable disposable = Observable.just(contentBinding.editLogin.getText().toString()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                contentBinding.textView.setText(s);
            }
        });

        contentBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().saveData(contentBinding.editLogin.getText().toString(), contentBinding.editPassword.getText().toString());
            }
        });

        addObserver(disposable);
        getPresenter().OnCreate();
        getPresenter().getUser();

    }


    public void observ() {
        Observable.combineLatest(RxHelperView.createTextChangeObservable(contentBinding.editLogin), RxHelperView.createTextChangeObservable(contentBinding.editPassword),
                new BiFunction<String, String, Boolean>() {
                    @Override
                    public Boolean apply(String login, String password) throws Exception {
                        double x = (password.length() * 100) / 6;
                        updateProgressBar((int) x);
                        return login.length() > 3 && password.length() > 3;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        contentBinding.button.setText("divide by zero");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateProgressBar(int percent) {
        contentBinding.progressBar.setProgress(percent);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
        this.finish();
    }

    @Override
    public void buttonActivation(boolean state) {
        contentBinding.button.setText(state ? "Success" : "Fuck");
        contentBinding.button.setEnabled(state);
        contentBinding.progressBar.setVisibility(state ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showCredentials(String user, String login) {
        contentBinding.textView.setText("User:" + user + "\n Login:" + login);
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainViewPresenterImpl(this, new InteractorImpl(this));
    }
}
