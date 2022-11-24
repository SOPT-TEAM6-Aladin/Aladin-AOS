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

    private val _cartCount = MutableLiveData<MutableList<Int>>()
    val cartCount: LiveData<MutableList<Int>> = _cartCount

    val cartSelectedAll = MutableLiveData(false)

    init {
        _cartResult.value = tmpList
        _cartCount.value = MutableList<Int>(tmpList.size) { 1 }
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

    fun calculateTotalPrice() {
        var totalPrice = 0
        for (i in 0 until _cartResult.value!!.size) {
            totalPrice = _cartResult.value!![i].price * _cartCount.value!![i]
        }
        _cartTotalPrice.value = totalPrice
    }

    fun setCount(index: Int): Int {
        return _cartCount.value!![index]
    }

    fun minusOnClick(index: Int) {
        _cartCount.value!![index] = _cartCount.value!![index] - 1
    }

    fun plusOnClick(index: Int) {
        _cartCount.value!![index] = _cartCount.value!![index] + 1
    }
}
