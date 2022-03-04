package com.melihkarakilinc.moviesapplication.Utils

import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.melihkarakilinc.moviesapplication.R

class Util {
    fun imageLoader(path: String, imageView: ImageView) {
        imageView.load(ApiUrl.POSTER_PATH + path) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            transformations(RoundedCornersTransformation())
        }
    }
}