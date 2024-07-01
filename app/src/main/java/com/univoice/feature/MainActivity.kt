package com.univoice.feature

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.univoice.R
import com.univoice.core_ui.base.BindingActivity
import com.univoice.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {
        initMainBottomNavigation()
    }

    private fun initMainBottomNavigation() {
        val navController = (supportFragmentManager.findFragmentById(R.id.fcv_main_nav) as NavHostFragment).findNavController()
        binding.bnvMainNav.apply {
            setupWithNavController(navController)
            itemIconTintList = null
        }

        setBottomNavigationVisible(navController)
    }

    private fun setBottomNavigationVisible(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnvMainNav.visibility =
                if (destination.id in
                    listOf(
                        R.id.fragment_home,
                        R.id.fragment_storage,
                        R.id.fragment_setting
                    )
                ) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }
    }
}