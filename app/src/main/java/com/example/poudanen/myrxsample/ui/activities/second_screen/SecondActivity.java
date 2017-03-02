package com.example.poudanen.myrxsample.ui.activities.second_screen;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.poudanen.myrxsample.R;
import com.example.poudanen.myrxsample.databinding.ActivitySecondBinding;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding activitySecondBinding;

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            VrPanoramaView.Options panoOptions = null;
            panoOptions = new VrPanoramaView.Options();
            panoOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
            activitySecondBinding.content.panoView.loadImageFromBitmap(bitmap, panoOptions);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

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
        Picasso.with(this).load("https://o.vrchive.com/index.php?url=s3-us-west-2.amazonaws.com/vrchiveupload1/2016/05/07/Witcher-LightHouse-SmartPhone-360-Stereo-2016-05-03-16-44-31.jpg").into(target);
    }

    @Override
    public void onDestroy() {  // could be in onPause or onStop
        Picasso.with(this).cancelRequest(target);
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
