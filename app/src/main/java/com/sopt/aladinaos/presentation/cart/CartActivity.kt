package com.sopt.aladinaos.presentation.cart

import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.aladinaos.R
import com.sopt.aladinaos.databinding.ActivityCartBinding
import com.sopt.aladinaos.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : BindingActivity<ActivityCartBinding>(R.layout.activity_cart) {
    private val cartViewModel: CartViewModel by viewModels()
    private val cartAdapter = CartAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = cartViewModel
        cartViewModel.getBasket()
        initAdapter()
        initBackBtnClickListener()
    }

    private fun initAdapter() {
        binding.rvCart.adapter = cartAdapter
        cartViewModel.cartResult.observe(this) { result ->
            cartAdapter.submitList(result)
        }
    }

    private fun initBackBtnClickListener() {
        binding.btnCartBack.setOnClickListener {
            finish()
        }
    }
}
