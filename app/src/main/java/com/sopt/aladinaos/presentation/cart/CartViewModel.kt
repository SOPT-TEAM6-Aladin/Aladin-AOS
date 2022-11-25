package com.sopt.aladinaos.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.data.entity.response.CartResponse
import com.sopt.aladinaos.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    private val cartResponse = MutableLiveData<List<CartResponse>>()

    private val _cartResult = MutableLiveData<MutableList<Book>>(mutableListOf())
    val cartResult: LiveData<MutableList<Book>> = _cartResult

    private val _cartTotalPrice = MutableLiveData<Int>()
    val cartTotalPrice: LiveData<Int> = _cartTotalPrice

    private val cartCount = MutableLiveData<MutableList<Int>>()

    private val _cartSelected = MutableLiveData<MutableList<Boolean>>()
    val cartSelected: LiveData<MutableList<Boolean>> = _cartSelected

    fun getBasket() {
        viewModelScope.launch {
            cartRepository.getBasket()
                .onSuccess { response ->
                    cartResponse.value = requireNotNull(response.data)
                    Timber.d("RESPONSE : ${cartResponse.value}")
                    val cartTmpList = mutableListOf<Book>()
                    for (i in 0 until cartResponse.value!!.size) {
                        cartTmpList.add(cartResponse.value!![i].book)
                        cartCount.value = MutableList(cartTmpList.size) { 1 }
                        _cartSelected.value = MutableList(cartTmpList.size) { true }
                    }
                    _cartResult.value = cartTmpList
                    calculateTotalPrice()
                }.onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }

    fun setCartSelectedTrue() {
        _cartSelected.value = MutableList(_cartResult.value!!.size) { true }
    }

    fun setCartSelectedFalse() {
        _cartSelected.value = MutableList(_cartResult.value!!.size) { false }
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
