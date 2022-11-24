package com.sopt.aladinaos.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.data.repository.CartRepository
import com.sopt.aladinaos.presentation.cart.CartActivity.Companion.tmpList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _cartResult = MutableLiveData<List<Book>>()
    val cartResult: LiveData<List<Book>> = _cartResult

    private val _cartTotalPrice = MutableLiveData<Int>()
    val cartTotalPrice: LiveData<Int> = _cartTotalPrice

    private val cartCount = MutableLiveData<MutableList<Int>>()

    private val _cartSelected = MutableLiveData<MutableList<Boolean>>()
    val cartSelected: LiveData<MutableList<Boolean>> = _cartSelected

    init {
        _cartResult.value = tmpList
        cartCount.value = MutableList(tmpList.size) { 1 }
        _cartSelected.value = MutableList(tmpList.size) { true }
    }

    /*  // 서버 구현 시 호출할 함수
        fun getBasket() {
            viewModelScope.launch {
                cartRepository.getBasket()
                    .onSuccess { response ->
                        _cartResult.value = requireNotNull(response.data)
                        Timber.d("${_cartResult.value}")
                    }.onFailure { throwable ->
                        Timber.e(throwable.message)
                    }
            }
        }
    */

    fun setCartSelectedTrue() {
        _cartSelected.value = MutableList(tmpList.size) { true }
    }

    fun setCartSelectedFalse() {
        _cartSelected.value = MutableList(tmpList.size) { false }
    }

    fun setCartCheckBoxSelected(index: Int): Boolean {
        return _cartSelected.value!![index]
    }

    fun calculateTotalPrice() {
        var totalPrice = 0
        for (i in 0 until _cartResult.value!!.size) {
            if (_cartSelected.value!![i]) {
                totalPrice += _cartResult.value!![i].price * cartCount.value!![i]
            }
        }
        _cartTotalPrice.value = totalPrice
    }

    fun setCartCount(index: Int): Int {
        return cartCount.value!![index]
    }

    fun minusOnClick(index: Int) {
        cartCount.value!![index] = cartCount.value!![index] - 1
        if (_cartSelected.value!![index]) {
            _cartTotalPrice.value = _cartTotalPrice.value!!.minus(_cartResult.value!![index].price)
        }
    }

    fun plusOnClick(index: Int) {
        cartCount.value!![index] = cartCount.value!![index] + 1
        if (_cartSelected.value!![index]) {
            _cartTotalPrice.value = _cartTotalPrice.value!!.plus(_cartResult.value!![index].price)
        }
    }

    fun checkBoxOnClick(index: Int, selected: Boolean) {
        _cartSelected.value!![index] = selected
        calculateTotalPrice()
    }
}
