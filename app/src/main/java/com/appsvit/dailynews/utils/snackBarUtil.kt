package com.appsvit.dailynews.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun snackBarUtil(
    view: View,
    msg: String,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar
        .make(view, msg, Snackbar.LENGTH_SHORT)
        .show()
}

