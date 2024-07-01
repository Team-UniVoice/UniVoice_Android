package com.univoice.feature.example

import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.core_ui.view.UiState
import com.univoice.databinding.ActivityExampleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ExampleActivity : BindingActivity<ActivityExampleBinding>(R.layout.activity_example) {
    private val viewModel by viewModels<ExampleViewModel>()

    override fun initView() {
        viewModel.getExampleRecyclerview(2)
        collectExample()
    }

    private fun collectExample() {
        viewModel.getExampleState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    binding.rvExample.adapter = ExampleAdapter(click = { _, _ ->
                    }).apply { submitList(it.data) }
                }

                is UiState.Empty -> Unit
                is UiState.Failure -> Unit
            }
        }.launchIn(lifecycleScope)
    }
}