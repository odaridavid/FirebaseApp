package com.github.odaridavid.firebaseapp

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val selectedImageImageView: ImageView by lazy {
        findViewById(R.id.selected_image_image_view)
    }

    fun uploadImage(view: View) {
         // TODO Select an image
        //  TODO Upload Image
    }

}