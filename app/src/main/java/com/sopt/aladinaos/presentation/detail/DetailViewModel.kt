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

    var heartCount = 3785
    private val _bookPrice = MutableLiveData<Int>()
    val bookPrice: LiveData<Int> = _bookPrice

    /** 서버에 해당 id의 책 상세 데이터 요청 */
    fun getBookDetail(id: Int) {
        viewModelScope.launch {
            detailRepository.getBookDetail(id)
                .onSuccess { response ->
                    Timber.d("GET BOOK DETAIL SUCCESS")
                    Timber.d("status : ${response.status}")
                    Timber.d("response : $response")
                    _detailResult.value = requireNotNull(response.data)

                    // 도서 정가 계산
                    _bookPrice.value = response.data.price
                    if (response.data.discountRate == 0) return@onSuccess

                    var discountRate = 0.01 * response.data.discountRate
                    _bookPrice.value = Math.round(_bookPrice.value!! * discountRate).toInt()
                    // ★★★ 테스트 후에 아래 Timber 다 지우기
                    Timber.d("Price : ${response.data.price}")
                    Timber.d("Discount Rate : ${response.data.discountRate}")
                    Timber.d("Discount Rate (Double) : $discountRate")
                    Timber.d("Before Round Price : ${_bookPrice.value!! * discountRate}")
                    Timber.d("After Round Price : ${_bookPrice.value}")
                }.onFailure { throwable ->
                    Timber.e("GET BOOK DETAIL FAIL")
                    Timber.e("fail message : ${throwable.message}")
                }
        }
    }
}
