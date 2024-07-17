package com.univoice.feature

import android.app.Activity
import android.content.Intent
import android.os.Handler
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivitySplashBinding
import com.univoice.feature.entry.EntryActivity
import com.univoice.feature.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun initView() {
        installSplashScreen()
        initSplash()
    }

    private fun initSplash() {
        Handler().postDelayed({
            runBlocking {
                when {
                    (loginViewModel.getUserAccessToken().toString().isNotBlank() &&
                            loginViewModel.getCheckLogin().first()) -> {
                        navigateTo<MainActivity>()
                    }

                    else -> {
                        navigateTo<EntryActivity>()
                    }
                }
                finish()
            }
        }, 3000)
    }

    private inline fun <reified T : Activity> navigateTo() {
        Intent(this@SplashActivity, T::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }
}