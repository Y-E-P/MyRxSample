package com.example.poudanen.myrxsample.ui.activities.main_screen;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.poudanen.myrxsample.R;
import com.example.poudanen.myrxsample.databinding.ActivityMainBinding;
import com.example.poudanen.myrxsample.databinding.ContentMainBinding;
import com.example.poudanen.myrxsample.ui.activities.base.BaseActivity;
import com.example.poudanen.myrxsample.ui.activities.second_screen.SecondActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    MainViewPresenterImpl<MainView> mMainPresenter;
    private ActivityMainBinding mainBinding;
    private ContentMainBinding contentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        getActivityComponent().inject(this);
        mMainPresenter.attachView(this);
        contentBinding = mainBinding.mainContent;
        setSupportActionBar(mainBinding.toolbar);
        contentBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.saveData(contentBinding.editLogin.getText().toString(), contentBinding.editPassword.getText().toString());
            }
        });
        mMainPresenter.OnCreate();
        mMainPresenter.getUser();
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
    public void showError(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void showCredentials(String user, String login) {
        contentBinding.textView.setText("User:" + user + "\n Login:" + login);
    }

    @Override
    public void showSomeText(String someText) {
        contentBinding.textViewDagger.setText(someText);
    }

}
