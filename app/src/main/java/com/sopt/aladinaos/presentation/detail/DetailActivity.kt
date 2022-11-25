package com.sopt.aladinaos.presentation.detail

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import com.sopt.aladinaos.R
import com.sopt.aladinaos.databinding.ActivityDetailBinding
import com.sopt.aladinaos.presentation.cart.CartActivity
import com.sopt.aladinaos.presentation.detail.DetailViewModel.Companion.State
import com.sopt.aladinaos.util.binding.BindingActivity
import com.sopt.aladinaos.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = detailViewModel

        getBookDetailData()
        initDetailResultObserve()
        initErrorMessageObserve()
        initBackBtnClickListener()
        initCartBtnClickListener()
        initCostTextView()
        initMoreBtnList()
    }

    private fun getBookDetailData() {
        if (intent.hasExtra("id")) {
            val id = intent.getIntExtra("id", 1)
            detailViewModel.getBookDetail(id)
        } else {
            this.showToast(getString(R.string.msg_error))
            finish()
        }
    }

    private fun initDetailResultObserve() {
        detailViewModel.detailResult.observe(this) {
            if (!it.pick) binding.ivDetailEditorBadge.visibility = View.GONE
            if (it.discountRate == 0) binding.tvDetailCost.visibility = View.GONE
        }
    }

    private fun initErrorMessageObserve() {
        detailViewModel.errorMessage.observe(this) {
            when (it) {
                State.NULL -> this.showToast(getString(R.string.msg_null))
                State.ERROR -> this.showToast(getString(R.string.msg_error))
                else -> this.showToast(getString(R.string.msg_unknown_error))
            }
        }
    }

    private fun initBackBtnClickListener() {
        binding.btnDetailBack.setOnClickListener {
            finish()
        }
    }

    private fun initCartBtnClickListener() {
        binding.btnDetailCart.setOnClickListener {
            val toCart = Intent(this, CartActivity::class.java)
            startActivity(toCart)
        }
    }

    private fun initCostTextView() {
        binding.tvDetailCost.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun initMoreBtnList() {
        initMoreBtn(binding.llDetailMoreIntro, binding.tvDetailBookIntro, 3)
        initMoreBtn(binding.llDetailMoreIndex, binding.tvDetailIndex, 3)
        initMoreBtn(binding.llDetailMoreSummary, binding.tvDetailSummary, 4)
    }

    /** 더보기 버튼 Visibility 및 클릭 리스너 설정 */
    private fun initMoreBtn(btn: LinearLayout, tv: TextView, maxLine: Int) {
        if (tv.lineCount <= maxLine) btn.visibility = View.GONE

        btn.setOnClickListener {
            tv.maxLines = Integer.MAX_VALUE
            btn.visibility = View.GONE
        }
    }
}
