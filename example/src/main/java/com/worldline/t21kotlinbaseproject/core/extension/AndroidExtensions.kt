package com.worldline.t21kotlinbaseproject.core.extension

import timber.log.Timber

/**
 * AndroidExtensions
 */

/**
 * Any
 * */
fun Any.info(text: String) {
    Timber.i(text)
}

fun Any.error(text: String) {
    Timber.e(text)
}

fun Any.error(text: String, exception: Exception) {
    Timber.e(exception, text)
}

fun Any.warn(text: String) {
    Timber.w(text)
}

fun Any.warn(text: String, exception: Exception) {
    Timber.w(exception, text)
}

fun Any.debug(text: String) {
    Timber.d(text)
}

fun Any.debug(text: String, exception: Exception) {
    Timber.d(exception, text)
}