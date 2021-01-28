package com.dara.ulessontask.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

class ViewUtils {
    fun showSnackbar(view: View, message:String){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    }
}