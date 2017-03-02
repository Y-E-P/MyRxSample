package com.example.poudanen.myrxsample.ui.activities.second_screen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.poudanen.myrxsample.R;
import com.example.poudanen.myrxsample.databinding.ActivitySecondBinding;
import com.example.poudanen.myrxsample.utils.VrBitmapShow;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = SecondActivity.class.getSimpleName();
    private ActivitySecondBinding activitySecondBinding;
    private VrBitmapShow target1, target2, target3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondBinding = DataBindingUtil.setContentView(SecondActivity.this, R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activitySecondBinding.content.panoView.setEventListener(new VrPanoramaEventListener());
        final Animation startAnimation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
        final Animation startAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
        final Animation startAnimation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
        startAnimation1.setStartOffset(0);
        startAnimation2.setStartOffset(50);
        startAnimation3.setStartOffset(100);
        activitySecondBinding.content.imageView.startAnimation(startAnimation1);
        activitySecondBinding.content.imageView2.startAnimation(startAnimation2);
        activitySecondBinding.content.imageView3.startAnimation(startAnimation3);

        startAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                activitySecondBinding.content.imageView.startAnimation(startAnimation1);
                activitySecondBinding.content.imageView2.startAnimation(startAnimation2);
                activitySecondBinding.content.imageView3.startAnimation(startAnimation3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        someMethod();
    }

    private void someMethod() {
        target1 = new VrBitmapShow(activitySecondBinding.content.panoView);
        target2 = new VrBitmapShow(activitySecondBinding.content.panoViewOne);
        target3 = new VrBitmapShow(activitySecondBinding.content.panoViewTwo);
        Picasso.with(this).load("https://o.vrchive.com/index.php?url=s3-us-west-2.amazonaws.com/vrchiveupload1/2016/05/07/Witcher-LightHouse-SmartPhone-360-Stereo-2016-05-03-16-44-31.jpg").into(target1);
        Picasso.with(this).load("https://raw.githubusercontent.com/googlevr/gvr-android-sdk/master/samples/sdk-simplepanowidget/src/main/assets/andes.jpg").into(target2);
        Picasso.with(this).load("https://developers.google.com/vr/images/concepts/vrview-mono-stereo.jpg").into(target3);
    }

    @Override
    public void onDestroy() {  // could be in onPause or onStop
        Picasso.with(this).cancelRequest(target1);
        Picasso.with(this).cancelRequest(target2);
        Picasso.with(this).cancelRequest(target3);
        activitySecondBinding.content.panoView.shutdown();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        activitySecondBinding.content.panoView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activitySecondBinding.content.panoView.resumeRendering();
    }
}
