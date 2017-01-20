package com.example.poudanen.myrxsample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.poudanen.myrxsample.model.UserCredentials;
import com.example.poudanen.myrxsample.ui.RxHelperView;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText loginEdit, passwordEdit;
    private Button button;
    private TextView textView;
    private LinearLayout linearLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginEdit = (EditText) findViewById(R.id.edit_login);
        passwordEdit = (EditText) findViewById(R.id.edit_password);
        textView = (TextView) findViewById(R.id.textView);
        linearLayout = (LinearLayout) findViewById(R.id.lay);
        button = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        observ();
        Disposable p = RxHelperView.click(fab).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object aVoid) throws Exception {
                RxHelperView.dialog(MainActivity.this, R.string.app_name, R.string.app_name).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        MainActivity.this.addObserver(d);
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            test2();
                        } else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        textView.setText("Im complete");
                    }
                });
            }
        });


        Disposable x = Observable.just(loginEdit.getText().toString()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                textView.setText(s);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeysHelper.getInstance().save(MainActivity.this, passwordEdit.getText().toString(), "PASSWORD");
                KeysHelper.getInstance().save(MainActivity.this, loginEdit.getText().toString(), "NAME");
            }
        });

        addObserver(p);
        addObserver(x);

    }

    public void observ() {
        Observable.combineLatest(RxHelperView.createTextChangeObservable(loginEdit), RxHelperView.createTextChangeObservable(passwordEdit),
                new BiFunction<String, String, Boolean>() {
                    @Override
                    public Boolean apply(String login, String password) throws Exception {
                        double x = (password.length() * 100) / 6;
                        progressBar.setProgress((int) x);
                        return login.length() > 3 && password.length() > 3;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        button.setText(aBoolean ? "Success" : "Fuck");
                        button.setEnabled(aBoolean);
                        progressBar.setVisibility(aBoolean ? View.GONE : View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        button.setText("divide by zero");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void test2() {
        Flowable<UserCredentials> flowable = KeysHelper.getInstance().getUserObj(MainActivity.this)
                .filter(new Predicate<UserCredentials>() {
                    @Override
                    public boolean test(UserCredentials userCredentials) throws Exception {
                        return true;
                    }
                })
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Disposable textDisponce = flowable.subscribe(new Consumer<UserCredentials>() {
            @Override
            public void accept(UserCredentials userCredentials) throws Exception {
                textView.setText(userCredentials.toString());
            }
        });

        addObserver(textDisponce);
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
}
