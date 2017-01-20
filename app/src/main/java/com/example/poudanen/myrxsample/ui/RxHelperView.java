package com.example.poudanen.myrxsample.ui;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

/**
 * Created by poudanen on 20.01.17.
 * Class to provide an alternative of rxBinding for android,because it has no compatibility with RxAndroid 2.0
 */

public class RxHelperView {

    public static Observable<String> createTextChangeObservable(@NonNull final EditText editText) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
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
                if (!emitter.isDisposed()) {
                    emitter.onNext(editText.getText().toString());
                }
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        editText.removeTextChangedListener(watcher);
                    }
                });
                editText.addTextChangedListener(watcher);
            }
        });
    }

    /**
     * Sample Button RX listener
     *
     * @param view - param for listen
     * @return sample Object, because RxJava 2 pass null to onNext method.
     * java.lang.NullPointerException: onNext called with null. Null values are generally not allowed in 2.x operators and sources.
     */
    public static Observable<Object> click(@NonNull final View view) {
        return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(final ObservableEmitter<Object> emitter) throws Exception {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(new Object());
                        }
                    }
                });

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        if (!emitter.isDisposed()) {
                            view.setOnClickListener(null);
                        }
                    }
                });
            }
        });
    }
}
