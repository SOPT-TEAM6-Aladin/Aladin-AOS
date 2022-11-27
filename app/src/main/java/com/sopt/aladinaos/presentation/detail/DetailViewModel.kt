package com.sopt.aladinaos.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.aladinaos.data.entity.response.Detail
import com.sopt.aladinaos.data.entity.response.RequestAddToCartDto
import com.sopt.aladinaos.data.repository.AddRepository
import com.sopt.aladinaos.data.repository.DetailRepository
import com.sopt.aladinaos.data.repository.LikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    private val likeRepository: LikeRepository,
    private val addRepository: AddRepository
) : ViewModel() {
    private val _detailResult = MutableLiveData<Detail>()
    val detailResult: LiveData<Detail> = _detailResult

    private val _bookPrice = MutableLiveData<Int>()
    val bookPrice: LiveData<Int> = _bookPrice

    private val _isHeartActive = MutableLiveData<Boolean>()
    val isHeartActive: LiveData<Boolean> = _isHeartActive

    private val _likeCount = MutableLiveData<Int>()
    val likeCount: LiveData<Int> = _likeCount

    private val _toastMessage = MutableLiveData<State>()
    val toastMessage: LiveData<State> = _toastMessage

    /** 서버에 해당 id의 책 상세 데이터 요청 */
    fun getBookDetail(id: Int) {
        viewModelScope.launch {
            detailRepository.getBookDetail(id)
                .onSuccess { response ->
                    if (response.data == null) _toastMessage.value = State.NULL

                    Timber.d("GET BOOK DETAIL SUCCESS")
                    Timber.d("status : ${response.status}")
                    Timber.d("response : $response")
                    _detailResult.value = requireNotNull(response.data) { "response data is null" }
                    _likeCount.value = _detailResult.value!!.likeCount

                    // 도서 정가 계산
                    _bookPrice.value = response.data.price
                    if (response.data.discount == 0) return@onSuccess
                    var discountRate = 1 - (0.01 * response.data.discount)
                    _bookPrice.value = Math.round(_bookPrice.value!! * discountRate).toInt()
                }.onFailure { throwable ->
                    Timber.e("GET BOOK DETAIL FAIL")
                    Timber.e("fail message : ${throwable.message}")
                    _toastMessage.value = State.ERROR
                }
        }
    }

    /** 서버에 책 좋아요 반영 요청 */
    fun putLike(id: Int) {
        viewModelScope.launch {
            likeRepository.putLike(id)
                .onSuccess { response ->
                    if (response.data == null) _toastMessage.value = State.NULL
                    Timber.d("PUT LIKE SUCCESS")
                    Timber.d("status : ${response.status}")
                    Timber.d("response : $response")

                    _isHeartActive.value = response.data?.hasLike
                    if (response.data?.hasLike == true) {
                        _toastMessage.value = State.LIKE_SUCCESS
                        _likeCount.value = _likeCount.value?.plus(1) ?: 0
                    } else {
                        _toastMessage.value = State.LIKE_CANCEL
                        _likeCount.value = _likeCount.value?.minus(1) ?: 0
                    }
                }
                .onFailure { throwable ->
                    Timber.e("PUT LIKE FAIL")
                    Timber.e("fail message : ${throwable.message}")
                    _toastMessage.value = State.ERROR
                }
        }
    }

    fun addToCart(id: Int) {
        viewModelScope.launch {
            addRepository.addToCart(RequestAddToCartDto(id))
                .onSuccess { response ->
                    if (response.data == null) _toastMessage.value = State.NULL
                    Timber.d("ADD TO CART SUCCESS")
                    Timber.d("status : ${response.status}")
                    Timber.d("response : $response")

                    _toastMessage.value = State.CART_SUCCESS
                }
                .onFailure { throwable ->
                    Timber.e("ADD TO CART FAIL")
                    Timber.e("throwable : $throwable")
                    if (throwable is HttpException) {
                        when (throwable.code()) {
                            CART_EXIST_CODE -> _toastMessage.value = State.CART_EXIST
                            else -> _toastMessage.value = State.ERROR
                        }
                    }
                }
        }
    }

    companion object {
        enum class State {
            NULL, ERROR,
            LIKE_SUCCESS, LIKE_CANCEL,
            CART_SUCCESS, CART_EXIST
        }

        private const val CART_EXIST_CODE = 400
    }
}
