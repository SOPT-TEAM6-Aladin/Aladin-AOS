package com.sopt.aladinaos.presentation.detail

import android.content.Intent
import android.os.Bundle
import com.sopt.aladinaos.R
import com.sopt.aladinaos.databinding.ActivityDetailBinding
import com.sopt.aladinaos.presentation.cart.CartActivity
import com.sopt.aladinaos.util.binding.BindingActivity

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBackBtnClickListener()
        initCartBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.btnDetailBack.setOnClickListener {
            finish()
        }
    }

    private fun initCartBtnClickListener() {
        binding.btnDetailCart.setOnClickListener {
            val toCart = Intent(this, CartActivity::class.java)
            startActivity(toCart)
        }
    }
}
