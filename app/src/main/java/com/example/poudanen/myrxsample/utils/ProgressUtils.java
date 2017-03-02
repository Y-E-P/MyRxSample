package com.example.poudanen.myrxsample.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.poudanen.myrxsample.R;
import com.example.poudanen.myrxsample.databinding.ProgressDialogBinding;

/**
 * Created by poudanen on 07.02.17.
 */

public final class ProgressUtils {

    public static ProgressDialog showLoadingDialog(Context context) {
        final ProgressDialogBinding progressDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.progress_dialog, null, false);
        final Animation startAnimation1 = AnimationUtils.loadAnimation(context, R.anim.blinking_animation);
        final Animation startAnimation2 = AnimationUtils.loadAnimation(context, R.anim.blinking_animation);
        final Animation startAnimation3 = AnimationUtils.loadAnimation(context, R.anim.blinking_animation);
        startAnimation1.setStartOffset(0);
        startAnimation2.setStartOffset(50);
        startAnimation3.setStartOffset(100);
        progressDialogBinding.imageView.startAnimation(startAnimation1);
        progressDialogBinding.imageView2.startAnimation(startAnimation2);
        progressDialogBinding.imageView3.startAnimation(startAnimation3);

        startAnimation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                progressDialogBinding.imageView.startAnimation(startAnimation1);
                progressDialogBinding.imageView2.startAnimation(startAnimation2);
                progressDialogBinding.imageView3.startAnimation(startAnimation3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(progressDialogBinding.getRoot());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
