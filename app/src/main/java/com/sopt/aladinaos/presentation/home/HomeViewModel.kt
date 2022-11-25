package com.sopt.aladinaos.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.aladinaos.data.ServicePool
import com.sopt.aladinaos.data.entity.response.BaseResponse
import com.sopt.aladinaos.data.entity.response.HomeData
import com.sopt.aladinaos.data.entity.response.HomeData.Pick
import com.sopt.aladinaos.data.entity.response.HomeData.Topic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel : ViewModel() {
    val isResponseSuccessful = MutableLiveData(false)
    private val _editorPickResult = MutableLiveData<List<Pick>>()
    val editorPickResult: LiveData<List<Pick>>
        get() = _editorPickResult

    private val _topSellerResult = MutableLiveData<List<Topic>>()
    val topSellerResult: LiveData<List<Topic>>
        get() = _topSellerResult

    private val homeService = ServicePool.homeService

    fun getPickData() {
        homeService.getHomeData().enqueue(object : Callback<BaseResponse<HomeData>> {
            override fun onResponse(
                call: Call<BaseResponse<HomeData>>,
                response: Response<BaseResponse<HomeData>>
            ) {
                if (response.isSuccessful) { // 통신 성공
                    isResponseSuccessful.value = true
                    _editorPickResult.value = response.body()?.data?.pick
                    _topSellerResult.value = response.body()?.data?.topic

//                    Timber.d("${response.body()}")
                    Log.d("HomeViewModel", response.body().toString())

                    Timber.d("${_editorPickResult.value}")
                    Timber.d("${_topSellerResult.value}")
                }
            }

            override fun onFailure(call: Call<BaseResponse<HomeData>>, t: Throwable) { // 통신 실패
                isResponseSuccessful.value = false
                Timber.d("${t.message}")
            }
        })
    }
}
