package com.example.poudanen.myrxsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.poudanen.myrxsample.model.UserCredentials;
import com.jakewharton.rxbinding.view.RxView;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import rx.functions.Action1;

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
        RxView.clicks(fab).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                test2(fab);
            }
        });

        Observable.just(loginEdit.getText().toString()).subscribe(new Consumer<String>() {
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


    }

    public void observ() {
        Flowable.combineLatest(new Publisher<EditText>() {
            @Override
            public void subscribe(Subscriber<? super EditText> s) {

            }
        }, new Publisher<EditText>() {
            @Override
            public void subscribe(Subscriber<? super EditText> s) {

            }
        }, new BiFunction<EditText, EditText, Object>() {
            @Override
            public Object apply(EditText editText, EditText editText2) throws Exception {
                return null;
            }
        });
        Observable.combineLatest(createTextChangeObservable(loginEdit), createTextChangeObservable(passwordEdit),
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

    public void test2(FloatingActionButton fab) {
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

    public Observable<String> subsEdit(final EditText editText) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(s.toString());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });
    }

    private Observable<String> createTextChangeObservable(@NonNull final EditText editText) {

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                //3
                final TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }

                    //4
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(s.toString());
                        }
                    }
                };


                //6
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        editText.removeTextChangedListener(watcher);
                    }
                });
                //5
                editText.addTextChangedListener(watcher);
                emitter.onNext(editText.getText().toString());
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
}
