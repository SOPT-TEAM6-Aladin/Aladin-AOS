package com.sopt.aladinaos.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.data.repository.CartRepository
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

    private val _cartPrice = MutableLiveData<Int>()
    val cartPrice: LiveData<Int> = _cartPrice

    private val _cartSelectedAll = MutableLiveData<Boolean>()
    val cartSelectedAll: LiveData<Boolean> = _cartSelectedAll

    fun getBasket() {
        Timber.d("getBasket 실행됨")
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
}
