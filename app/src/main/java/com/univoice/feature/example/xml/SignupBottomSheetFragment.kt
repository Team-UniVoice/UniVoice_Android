package com.univoice.feature.example.xml

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.univoice.databinding.FragmentSignupBottomSheetBinding

class SignupBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSignupBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkboxBottomSheetAgreeAll.setOnClickListener {
            val isChecked = binding.checkboxBottomSheetAgreeAll.isChecked
            binding.checkboxBottomSheetService.isChecked = isChecked
            binding.checkboxBottomSheetUse.isChecked = isChecked
            updateAgreeButtonState()
        }

        binding.checkboxBottomSheetService.setOnClickListener { updateAgreeButtonState() }
        binding.checkboxBottomSheetUse.setOnClickListener { updateAgreeButtonState() }

        binding.btnBottomSheetAgree.setOnClickListener {
            startActivity(Intent(requireContext(), CheckInfoActivity::class.java))
            dismiss()
        }
    }

    private fun updateAgreeButtonState() {
        binding.btnBottomSheetAgree.isEnabled = binding.checkboxBottomSheetService.isChecked &&
                binding.checkboxBottomSheetUse.isChecked
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
