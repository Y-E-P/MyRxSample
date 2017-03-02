package com.example.poudanen.myrxsample.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by poudanen on 02.03.17.
 */

public class VrBitmapShow implements Target {

    private VrPanoramaView panoramaView;

    public VrBitmapShow(VrPanoramaView panoramaView) {
        this.panoramaView = panoramaView;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        VrPanoramaView.Options panoOptions = null;
        panoOptions = new VrPanoramaView.Options();
        panoOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        panoramaView.loadImageFromBitmap(bitmap, panoOptions);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
