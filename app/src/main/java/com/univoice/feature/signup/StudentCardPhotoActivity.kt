package com.univoice.feature.signup
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
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
import com.univoice.databinding.ActivityStudentCardPhotoBinding
import com.univoice.feature.util.setupToolbarClickListener
import dagger.hilt.android.AndroidEntryPoint
import com.univoice.feature.signup.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.signup.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.signup.StudentIdInputActivity.Companion.USER_YEAR_KEY
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.min

@AndroidEntryPoint
class StudentCardPhotoActivity :
    BindingActivity<ActivityStudentCardPhotoBinding>(R.layout.activity_student_card_photo) {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var pickImagesLauncher: ActivityResultLauncher<Intent>
    private var isProcessingImage = false

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
        binding.btnStudentCardPhotoUpload.setOnClickListener {
            if (!isProcessingImage) {
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
                    isProcessingImage = true
                    setButtonsEnabled(false)
                    try {
                        val bitmap = decodeSampledBitmapFromUri(selectedImageUri, 1024, 1024)
                        val rotatedBitmap = handleImageRotation(selectedImageUri, bitmap)
                        val compressedBitmap = compressBitmap(rotatedBitmap)
                        val drawable = RoundedBitmapDrawableFactory.create(resources, compressedBitmap).apply {
                            cornerRadius = 10f * resources.displayMetrics.density
                        }
                        val compressedImageUri = saveBitmapToFile(compressedBitmap)
                        with(binding) {
                            btnStudentCardPhotoUpload.background = drawable
                            btnStudentCardPhotoUpload.text = ""
                            btnStudentCardPhotoNext.visibility = View.VISIBLE
                            btnStudentCardPhotoNext.setOnClickListener {
                                navigateToInfoInput(compressedImageUri)
                            }
                        }
                    } catch (e: IOException) {
                    } finally {
                        isProcessingImage = false
                        setButtonsEnabled(true)
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

    private fun decodeSampledBitmapFromUri(uri: Uri, reqWidth: Int, reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }

        contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, options)
        }

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        options.inJustDecodeBounds = false
        return contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, options)!!
        } ?: throw IOException("Unable to decode bitmap from URI")
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    private fun compressBitmap(bitmap: Bitmap): Bitmap {
        val outputStream = ByteArrayOutputStream()
        var compressRate = 100
        var compressedImageSize: Long

        // Calculate the initial size
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val initialImage = outputStream.toByteArray()
        val initialImageSize = initialImage.size.toLong()
        Log.d("ImageCompression", "Initial image size: ${bytesToMegabytes(initialImageSize)} MB")

        outputStream.reset()

        bitmap.compress(Bitmap.CompressFormat.JPEG, compressRate, outputStream)
        var compressedImage = outputStream.toByteArray()
        compressedImageSize = compressedImage.size.toLong()

        while (compressedImageSize > MAX_FILE_SIZE && compressRate > 0) {
            compressRate -= 5
            outputStream.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressRate, outputStream)
            compressedImage = outputStream.toByteArray()
            compressedImageSize = compressedImage.size.toLong()
        }

        Log.d("ImageCompression", "Compressed image size: ${bytesToMegabytes(compressedImageSize)} MB")

        return BitmapFactory.decodeByteArray(compressedImage, 0, compressedImage.size)
    }

    private fun saveBitmapToFile(bitmap: Bitmap): Uri {
        val file = File(externalCacheDir, "compressed_image.jpg")
        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()
        return Uri.fromFile(file)
    }

    private fun bytesToMegabytes(bytes: Long): Double {
        return bytes / (1024.0 * 1024.0)
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

    private fun navigateToInfoInput(imageUri: Uri) {
        Intent(this, InfoInputActivity::class.java).apply {
            putExtra(USER_IMAGE_KEY, imageUri.toString())
            putExtra(USER_YEAR_KEY, intent.getStringExtra(USER_YEAR_KEY))
            putExtra(SCHOOL_KEY, intent.getStringExtra(SCHOOL_KEY))
            putExtra(DEPARTMENT_KEY, intent.getStringExtra(DEPARTMENT_KEY))
            startActivity(this)
        }
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        binding.btnStudentCardPhotoUpload.isEnabled = enabled
        binding.btnStudentCardPhotoNext.isEnabled = enabled
    }

    companion object {
        const val USER_IMAGE_KEY = "selectedImageUri"
        const val MAX_FILE_SIZE = 512 * 512
    }
}
