package com.univoice.feature.example.xml

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.util.context.toast
import com.univoice.databinding.ActivityStudentCardPhotoBinding
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentCardPhotoActivity :
    BindingActivity<ActivityStudentCardPhotoBinding>(R.layout.activity_student_card_photo) {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var pickImagesLauncher: ActivityResultLauncher<Intent>

    override fun initView() {
        setupToolbar()
        setupPermissions()
        setupImagePicker()
        uploadBtnClickListener()
    }

    private fun setupToolbar() {
        with(binding.toolbarStudentCardPhoto) {
            tvToolbarTitle.text =
                applicationContext.getString(R.string.tv_toolbar_student_card_photo_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun uploadBtnClickListener() {
        binding.btnStudentCardPhotoUpload.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                if (allPermissionsGranted()) {
                    pickImageFromGallery()
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun setupPermissions() {
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                pickImageFromGallery()
            }
        }
    }

    private fun setupImagePicker() {
        pickImagesLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val selectedImageUri: Uri? = result.data?.data
                selectedImageUri?.let {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                    val drawable = RoundedBitmapDrawableFactory.create(resources, bitmap).apply {
                        cornerRadius = 10f * resources.displayMetrics.density
                    }
                    with(binding) {
                        btnStudentCardPhotoUpload.background = drawable
                        btnStudentCardPhotoUpload.text = ""
                        btnStudentCardPhotoNext.visibility = View.VISIBLE
                        btnStudentCardPhotoNext.setOnClickListener {
                            navigateToNameInput(selectedImageUri)
                        }
                    }
                }
            }
        }

    }

    private fun allPermissionsGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun pickImageFromGallery() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            pickImagesLauncher.launch(this)
        }
    }

    private fun navigateToNameInput(selectedImageUri: Uri) {
        Intent(this, NameInputActivity::class.java).apply {
            putExtra(IMAGE_KEY, selectedImageUri.toString())
            startActivity(this)
        }
    }

    companion object {
        const val IMAGE_KEY = "selectedImageUri"
    }
}
