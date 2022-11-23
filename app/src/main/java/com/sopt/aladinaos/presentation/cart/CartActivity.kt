package com.sopt.aladinaos.presentation.cart

import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.aladinaos.R
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.databinding.ActivityCartBinding
import com.sopt.aladinaos.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : BindingActivity<ActivityCartBinding>(R.layout.activity_cart) {
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = cartViewModel
        // cartViewModel.getBasket()
        initAdapter()
        initBackBtnClickListener()
        initCartListObserve()
    }

    private fun initAdapter() {
        cartAdapter = CartAdapter(
            updateCount = cartViewModel::updateCartResult
        )
        binding.rvCart.adapter = cartAdapter
//        cartViewModel.cartResult.observe(this) { result ->
        cartAdapter.submitList(tmpList)
//        }
    }

    private fun initCartListObserve() {
        cartViewModel.cartResult.observe(this) { book ->
            cartAdapter.submitList(book)
            cartViewModel.calculateTotalPrice()
        }
    }

    private fun initBackBtnClickListener() {
        binding.btnCartBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        val tmpList = listOf(
            Book(1, "코틀린을 배워보자", "0", 10800, 10, 100),
            Book(2, "코틀린을 배워보자", "0", 10800, 10, 100),
            Book(3, "코틀린을 배워보자", "0", 10800, 10, 100),
            Book(4, "코틀린을 배워보자", "0", 10800, 10, 100),
            Book(5, "코틀린을 배워보자", "0", 10800, 10, 100),
            Book(6, "코틀린을 배워보자", "0", 10800, 10, 100)
        )
    }
}
