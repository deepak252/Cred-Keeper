package com.dcapp.creds_keeper.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtils{
    fun hideKeyboard(context : Context, view: View) {
        // Hide the soft keyboard when the user taps outside an EditText
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(context : Context, view: View) {
        // Hide the soft keyboard when the user taps outside an EditText
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT)
    }
}