package com.jagrod.weather.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * @author Juan Agustín
 * @since 2/8/17.
 */
fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit): Boolean {
    val editor = edit()
    editor.func()
    editor.apply()
    return true
}

// Logging
fun Any.debug(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.d("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.debug(msg: Any? = "Debug", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.d("TAG $tag", msg?.toString() ?: "null", thr)
}

fun Any.info(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.i("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.info(msg: Any? = "Info", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.i("TAG $tag", msg?.toString() ?: "null", thr)
}

fun Any.wtf(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.wtf("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.wtf(msg: Any? = "WTF", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.wtf("TAG $tag", msg?.toString() ?: "null", thr)
}

fun Any.warn(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.w("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.warn(msg: Any? = "Warn", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.w("TAG $tag", msg?.toString() ?: "null", thr)
}

fun Any.error(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.e("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.error(msg: Any? = "Error", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.e("TAG $tag", msg?.toString() ?: "null", thr)
}

fun Any.verbose(msg: () -> Any?, tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.v("TAG $tag", msg()?.toString() ?: "null", thr)
}

fun Any.verbose(msg: Any? = "Error", tag: String = this.javaClass.getTag(), thr: Throwable? = null) {
    Log.v("TAG $tag", msg?.toString() ?: "null", thr)
}

private fun Class<*>.getTag(): String {
    val tag = simpleName
    return if (tag.length <= 19) tag
    else tag.substring(0, 19)
}

fun ImageView.loadUrl(url: String) {
    Picasso.with(this.context).load(url).fit().centerCrop().into(this)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}