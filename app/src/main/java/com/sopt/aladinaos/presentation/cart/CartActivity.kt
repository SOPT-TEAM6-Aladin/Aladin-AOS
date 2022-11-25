package com.sopt.aladinaos.presentation.cart

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sopt.aladinaos.R
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
        initAdapter()
        cartViewModel.getBasket()
        cartViewModel.calculateTotalPrice()
        initTotalPrice()
        initBackBtnClickListener()
        initCheckBox()
    }

    private fun initAdapter() {
        cartAdapter = CartAdapter(
            setSelected = cartViewModel::setCartCheckBoxSelected,
            checkBoxOnClick = ::checkBoxOnClick,
            setCount = cartViewModel::setCartCount,
            plusOnClick = ::plusOnClick,
            minusOnClick = ::minusOnClick
        )
        binding.rvCart.adapter = cartAdapter
        val animator = binding.rvCart.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
        cartViewModel.cartResult.observe(this) { cartResult ->
            cartAdapter.submitList(cartResult)
        }
    }

    private fun initCheckBox() {
        binding.cbCart.isChecked = true
        binding.cbCart.setOnClickListener {
            if (binding.cbCart.isChecked) {
                cartViewModel.setCartSelectedTrue()
            } else {
                cartViewModel.setCartSelectedFalse()
            }
            for (i in 0..cartViewModel.cartResult.value!!.size) {
                cartAdapter.notifyItemChanged(i)
            }
            cartViewModel.calculateTotalPrice()
        }
    }

    private fun initTotalPrice() {
        cartViewModel.calculateTotalPrice()
    }

    private fun checkBoxOnClick(index: Int, selected: Boolean) {
        cartViewModel.checkBoxOnClick(index, selected)
        cartAdapter.notifyItemChanged(index)
        binding.cbCart.isChecked = cartViewModel.cartSelected.value!!.all { it }
    }

    private fun plusOnClick(index: Int) {
        cartViewModel.plusOnClick(index)
        cartAdapter.notifyItemChanged(index)
    }

    private fun minusOnClick(index: Int) {
        cartViewModel.minusOnClick(index)
        cartAdapter.notifyItemChanged(index)
    }

    private fun initBackBtnClickListener() {
        binding.btnCartBack.setOnClickListener {
            finish()
        }
    }
}
