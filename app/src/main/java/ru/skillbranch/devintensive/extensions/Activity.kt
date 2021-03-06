package ru.skillbranch.devintensive.extensions

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.utils.Utils

fun Activity.hideKeyboard() {
    val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun Activity.isKeyboardOpen(): Boolean {
    val rootView = findViewById<View>(R.id.content)
    val visibleBounds = Rect()
    rootView.getWindowVisibleDisplayFrame(visibleBounds)

    val heightDiff = rootView.height - visibleBounds.height()
    val marginOfError = Utils.convertDpToPx(this, 50f)
    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean = isKeyboardOpen().not()