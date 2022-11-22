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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = cartViewModel
        cartViewModel.getBasket()
    }
}
