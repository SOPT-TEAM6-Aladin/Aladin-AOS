package com.sopt.aladinaos.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.sopt.aladinaos.R
import com.sopt.aladinaos.databinding.ActivityMainBinding
import com.sopt.aladinaos.presentation.cart.CartActivity
import com.sopt.aladinaos.presentation.home.HomeFragment
import com.sopt.aladinaos.presentation.profile.ProfileFragment
import com.sopt.aladinaos.util.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        moveFragment(HomeFragment())
        binding.botNavMain.selectedItemId = R.id.menu_home
        binding.botNavMain.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_profile -> {
                    moveFragment(ProfileFragment())
                }
                R.id.menu_home -> {
                    moveFragment(HomeFragment())
                }
                R.id.menu_cart -> {
                    val toCart = Intent(this, CartActivity::class.java)
                    startActivity(toCart)
                }
                else -> error(getString(R.string.bot_navi_error, menu.itemId))
            }
            true
        }
    }

    private fun moveFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fc_main, fragment)
            .commit()
    }
}
