package com.sopt.aladinaos.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.sopt.aladinaos.R
import com.sopt.aladinaos.databinding.ActivityMainBinding
import com.sopt.aladinaos.presentation.cart.CartFragment
import com.sopt.aladinaos.presentation.home.HomeFragment
import com.sopt.aladinaos.presentation.profile.ProfileFragment
import com.sopt.aladinaos.util.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        moveToFragment(HomeFragment())
        binding.botNavMain.selectedItemId = R.id.menu_home
        binding.botNavMain.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_profile -> {
                    moveToFragment(ProfileFragment())
                }
                R.id.menu_home -> {
                    moveToFragment(HomeFragment())
                }
                R.id.menu_cart -> {
                    moveToFragment(CartFragment())
                }
                else -> error(getString(R.string.bot_navi_error, menu.itemId))
            }
            true
        }
    }

    private fun moveToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fc_main, fragment)
            .commit()
    }
}
