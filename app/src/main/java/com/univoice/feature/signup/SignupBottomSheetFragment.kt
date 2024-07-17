package com.univoice.feature.signup

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.univoice.R
import com.univoice.core_ui.base.BindingBottomSheetFragment
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.FragmentSignupBottomSheetBinding
import com.univoice.feature.signup.CreateAccountActivity.Companion.ID_KEY
import com.univoice.feature.signup.CreateAccountActivity.Companion.PASSWORD_KEY
import com.univoice.feature.signup.DepartmentInputActivity.Companion.DEPARTMENT_KEY
import com.univoice.feature.signup.InfoInputActivity.Companion.USER_ID_KEY
import com.univoice.feature.signup.InfoInputActivity.Companion.USER_NAME_KEY
import com.univoice.feature.signup.SchoolInputActivity.Companion.SCHOOL_KEY
import com.univoice.feature.signup.StudentCardPhotoActivity.Companion.USER_IMAGE_KEY
import com.univoice.feature.signup.StudentIdInputActivity.Companion.USER_YEAR_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@AndroidEntryPoint
class SignupBottomSheetFragment :
    BindingBottomSheetFragment<FragmentSignupBottomSheetBinding>(R.layout.fragment_signup_bottom_sheet) {

    private val viewModel by viewModels<SignupBottomSheetFragmentViewModel>()

    override fun initView() {
        setupListeners()
        observeSignupState()
    }

    private fun setupListeners() {
        with(binding) {
            cbBottomSheetAgreeAll.setOnClickListener { handleAgreeAllClick() }
            cbBottomSheetService.setOnClickListener { handleServiceCheckBoxClick() }
            cbBottomSheetUse.setOnClickListener { handleServiceCheckBoxClick() }
            btnBottomSheetAgree.setOnClickListener { handleAgreeButtonClick() }
        }
    }

    private fun handleAgreeAllClick() {
        with(binding) {
            val isChecked = cbBottomSheetAgreeAll.isChecked
            cbBottomSheetService.isChecked = isChecked
            cbBottomSheetUse.isChecked = isChecked
            updateAgreeButtonState()
        }
    }

    private fun handleServiceCheckBoxClick() {
        handleIndividualCheckBoxClick()
    }

    private fun handleIndividualCheckBoxClick() {
        with(binding) {
            val allChecked = cbBottomSheetService.isChecked && cbBottomSheetUse.isChecked
            cbBottomSheetAgreeAll.isChecked = allChecked
            updateAgreeButtonState()
        }
    }

    private fun handleAgreeButtonClick() {
        val admissionNumber = requireArguments().getString(USER_YEAR_KEY)!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val name = requireArguments().getString(USER_NAME_KEY)!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val studentNumber = requireArguments().getString(USER_ID_KEY)!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val email = requireArguments().getString(ID_KEY)!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val password = requireArguments().getString(PASSWORD_KEY)!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val universityName = requireArguments().getString(SCHOOL_KEY)!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val departmentName = requireArguments().getString(DEPARTMENT_KEY)!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val userImagePath = requireArguments().getString(USER_IMAGE_KEY)
        val studentCardImage = userImagePath?.let {
            val file = getFileFromUri(Uri.parse(it))
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("studentCardImage", file.name, requestFile)
        }
        viewModel.signUp(
            admissionNumber,
            name,
            studentNumber,
            email,
            password,
            universityName,
            departmentName,
            studentCardImage
        )
    }

    private fun updateAgreeButtonState() {
        with(binding) {
            btnBottomSheetAgree.isEnabled =
                cbBottomSheetService.isChecked && cbBottomSheetUse.isChecked
        }
    }

    private fun observeSignupState() {
        lifecycleScope.launch {
            viewModel.signupState.collect { state ->
                when (state) {
                    is UiState.Loading -> Unit
                    is UiState.Success -> navigateToCheckInfo()
                    is UiState.Failure -> showError(state.msg)
                    else -> Unit
                }
            }
        }
    }

    private fun navigateToCheckInfo() {
        dismiss()
        Intent(requireContext(), CheckInfoActivity::class.java).apply {
            flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }

    private fun showError(message: String) {
        println("에러: $message")
    }

    private fun getFileFromUri(uri: Uri): File {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile("tempImage", null, requireContext().cacheDir)
        inputStream?.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return tempFile
    }
}
