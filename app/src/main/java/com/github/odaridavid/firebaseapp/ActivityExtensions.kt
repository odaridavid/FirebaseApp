package com.github.odaridavid.firebaseapp

import android.app.Activity
import android.content.Intent

fun Activity.openImageIntent(requestCode: Int) {
    val pickFileIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
        type = "image/jpeg"
        putExtra(Intent.EXTRA_LOCAL_ONLY, true)
    }
    startActivityForResult(pickFileIntent, requestCode)
}