package com.sopt.aladinaos.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.data.repository.CartRepository
import com.sopt.aladinaos.presentation.cart.CartActivity.Companion.tmpList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
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
        _cartCount.value = MutableList<Int>(tmpList.size) { 0 }
    }

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

    fun calculateTotalPrice() {
        var count = 0
        _cartResult.value!!.forEach { book ->
            count += book.price * book.count
        }
        _cartTotalPrice.value = count
    }

    fun updateCartResult(count: Int, index: Int) {
        _cartResult.value = cartResult.value!!.mapIndexed { i, book ->
            if (i == index) {
                return@mapIndexed book.copy(count = count)
            }
            book
        }
    }
}
