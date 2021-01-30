package com.github.odaridavid.firebaseapp

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val selectedImageImageView: ImageView by lazy {
        findViewById(R.id.selected_image_image_view)
    }

    fun selectImage(view: View) {
        openImageIntent(requestCode = REQUEST_CODE_IMAGE_PICKER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE_PICKER) {
            val selectedImageUri: Uri? = data?.data
            val storageReference =
                Firebase.storage.reference.child("images/{${selectedImageUri?.lastPathSegment}}")
            selectedImageUri?.let { imageUri ->
                storageReference
                    .putFile(imageUri)
                    .continueWithTask {
                        storageReference.downloadUrl
                    }
                    .addOnSuccessListener { savedImageUri ->
                        showSuccessToast()

                        bindImageToImageView(imageUri = savedImageUri)
                    }
                    .addOnFailureListener { exception ->
                        showFailureToast()

                        Log.e("Error", exception.message ?: "No clue :)")
                    }
            }
        }
    }

    private fun showFailureToast() {
        showToast("It was not successful")
    }

    private fun showSuccessToast() {
        showToast("It was successful")
    }

    private fun bindImageToImageView(imageUri: Uri) {
        Glide.with(this@MainActivity)
            .load(imageUri)
            .into(selectedImageImageView)
    }

    companion object {
        private const val REQUEST_CODE_IMAGE_PICKER = 100
    }
}