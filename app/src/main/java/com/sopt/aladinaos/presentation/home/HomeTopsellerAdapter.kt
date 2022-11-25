package com.sopt.aladinaos.presentation.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.aladinaos.data.entity.response.HomeData.Topic
import com.sopt.aladinaos.databinding.ItemHomeTopsellerBinding
import com.sopt.aladinaos.presentation.detail.DetailActivity

class HomeTopsellerAdapter(context: Context) :
    RecyclerView.Adapter<HomeTopsellerAdapter.HomeViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var topSellerList: List<Topic> = emptyList()

    class HomeViewHolder(
        private val binding: ItemHomeTopsellerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(book: Topic) {
            Glide.with(this.binding.root)
                .load(book.cover)
                .into(binding.ivHomeTopsellerThumbnail)
            binding.tvHomeTopsellerTitle.text = book.name
            binding.tvHomeTopsellerAuthor.text = book.intro

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("id", book.bookId)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeTopsellerBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(topSellerList[position])
    }

    override fun getItemCount(): Int {
        return topSellerList.size
    }

    fun setTopicList(bookList: List<Topic>) {
        this.topSellerList = bookList.toList()
        notifyDataSetChanged()
    }
}
