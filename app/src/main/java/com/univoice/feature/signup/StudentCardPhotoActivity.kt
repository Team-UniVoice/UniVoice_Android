package com.univoice.feature.signup

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityStudentCardPhotoBinding
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint
import com.univoice.feature.signup.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.signup.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.signup.StudentIdInputActivity.Companion.USER_YEAR_KEY
import java.io.IOException

@AndroidEntryPoint
class StudentCardPhotoActivity :
    BindingActivity<ActivityStudentCardPhotoBinding>(R.layout.activity_student_card_photo) {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var pickImagesLauncher: ActivityResultLauncher<Intent>

    override fun initView() {
        initToolbar()
        setupPermissions()
        setupImagePicker()
        uploadBtnClickListener()
    }

    private fun initToolbar() {
        with(binding.toolbarStudentCardPhoto) {
            tvToolbarTitle.text =
                applicationContext.getString(R.string.tv_toolbar_student_card_photo_title)
            setupToolbarClickListener(ibToolbarIcon)
        }
    }

    private fun uploadBtnClickListener() {
        binding.layoutStudentCardPhotoUpload.setOnClickListener {
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
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                        val rotatedBitmap = handleImageRotation(selectedImageUri, bitmap)
                        val drawable = RoundedBitmapDrawableFactory.create(resources, rotatedBitmap).apply {
                            cornerRadius = 10f * resources.displayMetrics.density
                        }
                        with(binding) {
                            btnStudentCardPhotoUpload.background = drawable
                            btnStudentCardPhotoUpload.text = ""
                            btnStudentCardPhotoNext.visibility = View.VISIBLE
                            btnStudentCardPhotoNext.setOnClickListener {
                                navigateToInfoInput(selectedImageUri)
                            }
                        }
                    } catch (e: IOException) {
                    }
                }
            }
        }
    }

    private fun handleImageRotation(uri: Uri, bitmap: Bitmap): Bitmap {
        val inputStream = contentResolver.openInputStream(uri)
        val exif = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ExifInterface(inputStream!!)
        } else {
            ExifInterface(uri.path!!)
        }
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        val matrix = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
        }

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
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

    private fun navigateToInfoInput(selectedImageUri: Uri) {
        Intent(this, InfoInputActivity::class.java).apply {
            putExtra(USER_IMAGE_KEY, selectedImageUri.toString())
            putExtra(USER_YEAR_KEY, intent.getStringExtra(USER_YEAR_KEY))
            putExtra(SCHOOL_KEY, intent.getStringExtra(SCHOOL_KEY))
            putExtra(DEPARTMENT_KEY, intent.getStringExtra(DEPARTMENT_KEY))
            startActivity(this)
        }
    }

    companion object {
        const val USER_IMAGE_KEY = "selectedImageUri"
    }
}