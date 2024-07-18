package com.univoice.feature.signup

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import com.univoice.R
import com.univoice.core_ui.base.BindingBottomSheetFragment
import com.univoice.core_ui.util.fragment.viewLifeCycle
import com.univoice.core_ui.util.fragment.viewLifeCycleScope
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import java.io.File

@AndroidEntryPoint
class SignupBottomSheetFragment :
    BindingBottomSheetFragment<FragmentSignupBottomSheetBinding>(R.layout.fragment_signup_bottom_sheet) {

    private val viewModel by viewModels<SignupBottomSheetFragmentViewModel>()

    override fun initView() {
        setupListeners()
        observePostSignup()
        initServiceArrowClickListener()
        initUseArrowClickListener()
    }

    private fun initUseArrowClickListener() {
        binding.ivBottomSheetUseArrow.setOnClickListener {
            navigateToComplaintWeb("https://massive-maple-b53.notion.site/430e2c92b8694ad6a8b4497f3a3b4452?pvs=4")
        }
    }

    private fun initServiceArrowClickListener() {
        binding.ivBottomSheetServiceArrow.setOnClickListener {
            navigateToComplaintWeb("https://massive-maple-b53.notion.site/426578b24235447abccaae359549cdb7?pvs=4")
        }
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
        val admissionNumber = requireArguments().getString(USER_YEAR_KEY) ?: ""
        val name = requireArguments().getString(USER_NAME_KEY) ?: ""
        val studentNumber = requireArguments().getString(USER_ID_KEY) ?: ""
        val email = requireArguments().getString(ID_KEY) ?: ""
        val password = requireArguments().getString(PASSWORD_KEY) ?: ""
        val universityName = requireArguments().getString(SCHOOL_KEY) ?: ""
        val departmentName = requireArguments().getString(DEPARTMENT_KEY) ?: ""
        val userImagePath = requireArguments().getString(USER_IMAGE_KEY) ?: ""
        val userImageUri = Uri.parse(userImagePath)
        val userImageFile = getFileFromUri(userImageUri)

        viewModel.postSignUp(
            admissionNumber = admissionNumber.substring(0, 2),
            name = name,
            studentNumber = studentNumber,
            email = email,
            password = password,
            universityName = universityName,
            departmentName = departmentName,
            studentCardImage = userImageFile
        )
    }

    private fun updateAgreeButtonState() {
        with(binding) {
            btnBottomSheetAgree.isEnabled =
                cbBottomSheetService.isChecked && cbBottomSheetUse.isChecked
        }
    }

    private fun observePostSignup() {
        viewModel.postSignupState.flowWithLifecycle(viewLifeCycle).onEach {
            when (it) {
                is UiState.Loading -> Timber.tag("signup").d("로딩")
                is UiState.Success -> navigateToCheckInfo()
                is UiState.Failure -> Timber.tag("signup").d(it.msg)
                is UiState.Empty -> Timber.tag("signup").d("비었음")
            }
        }.launchIn(viewLifeCycleScope)
    }

    private fun navigateToCheckInfo() {
        dismiss()
        Intent(requireContext(), CheckInfoActivity::class.java).apply {
            flags = FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
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

    private fun navigateToComplaintWeb(uri: String) {
        val urlIntentComplaint =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(uri),
            )
        startActivity(urlIntentComplaint)
    }
}
