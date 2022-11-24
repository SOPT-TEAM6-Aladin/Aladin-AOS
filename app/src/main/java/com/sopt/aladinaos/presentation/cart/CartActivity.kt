package com.sopt.aladinaos.presentation.cart

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.SimpleItemAnimator
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
        cartAdapter.submitList(tmpList)
    }

    private fun initCheckBox() {
        binding.cbCart.isChecked = true
        binding.cbCart.setOnClickListener {
            if (binding.cbCart.isChecked) {
                cartViewModel.setCartSelectedTrue()
            } else {
                cartViewModel.setCartSelectedFalse()
            }
            for (i in 0..tmpList.size) {
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

    companion object {
        val tmpList = listOf(
            Book(1, "코틀린을 배워보자", "0", 10800, 10, 100),
            Book(2, "코틀린을 배워보자", "0", 20800, 10, 100),
            Book(3, "코틀린을 배워보자", "0", 9900, 10, 100),
            Book(4, "코틀린을 배워보자", "0", 13400, 10, 100),
            Book(5, "코틀린을 배워보자", "0", 24900, 10, 100),
            Book(6, "코틀린을 배워보자", "0", 33800, 10, 100)
        )
    }
}
