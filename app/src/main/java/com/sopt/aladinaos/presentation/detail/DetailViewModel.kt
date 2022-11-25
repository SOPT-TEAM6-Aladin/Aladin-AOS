package com.sopt.aladinaos.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.aladinaos.data.entity.response.Detail
import com.sopt.aladinaos.data.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {
    private val _detailResult = MutableLiveData<Detail>()
    val detailResult: LiveData<Detail> = _detailResult

    private val _heartCount = MutableLiveData<Int>()
    val heartCount: LiveData<Int> = _heartCount

    private val _bookPrice = MutableLiveData<Int>()
    val bookPrice: LiveData<Int> = _bookPrice

    private val _errorMessage = MutableLiveData<State>()
    val errorMessage: LiveData<State> = _errorMessage

    init {
        _heartCount.value = 3785
    }

    /** 서버에 해당 id의 책 상세 데이터 요청 */
    fun getBookDetail(id: Int) {
        viewModelScope.launch {
            detailRepository.getBookDetail(id)
                .onSuccess { response ->
                    if (response.data == null) _errorMessage.value = State.NULL

                    Timber.d("GET BOOK DETAIL SUCCESS")
                    Timber.d("status : ${response.status}")
                    Timber.d("response : $response")
                    _detailResult.value = requireNotNull(response.data) { "response data is null" }

                    // 도서 정가 계산
                    _bookPrice.value = response.data.price
                    if (response.data.discountRate == 0) return@onSuccess
                    var discountRate = 0.01 * response.data.discountRate
                    _bookPrice.value = Math.round(_bookPrice.value!! * discountRate).toInt()

                    // ★★★ 테스트 후에 아래 가격 관련 Timber 다 지우기
                    Timber.d("Price : ${response.data.price}")
                    Timber.d("Discount Rate : ${response.data.discountRate}")
                    Timber.d("Discount Rate (Double) : $discountRate")
                    Timber.d("Before Round Price : ${_bookPrice.value!! * discountRate}")
                    Timber.d("After Round Price : ${_bookPrice.value}")
                }.onFailure { throwable ->
                    Timber.e("GET BOOK DETAIL FAIL")
                    Timber.e("fail message : ${throwable.message}")
                    _errorMessage.value = State.ERROR
                }
        }
    }

    companion object {
        enum class State {
            NULL, ERROR
        }
    }
}
