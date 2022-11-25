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
import com.sopt.aladinaos.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = detailViewModel
        // ★★★ intent에서 id 값 받아오기
        detailViewModel.getBookDetail(1)
        initDetailResultObserve()
        initBackBtnClickListener()
        initCartBtnClickListener()
        initCostTextView()
        initMoreBtnList()
    }

    private fun initDetailResultObserve() {
        detailViewModel.detailResult.observe(this) {
            if (!it.pick) binding.ivDetailEditorBadge.visibility = View.GONE
            if (it.discountRate == 0) binding.tvDetailCost.visibility = View.GONE
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
