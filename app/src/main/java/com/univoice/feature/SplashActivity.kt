package com.univoice.feature

import android.content.Intent
import android.os.Handler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivitySplashBinding
import com.univoice.feature.entry.EntryActivity

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash){

    override fun initView() {
        installSplashScreen()
        initSplash()
    }

    private fun initSplash() {
        binding.lottieSplashLogo.playAnimation()
        Handler().postDelayed({
            startActivity(Intent(this, EntryActivity::class.java))
            finish()
        }, 3000)
    }
}