package com.sopt.aladinaos.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.aladinaos.R
import com.sopt.aladinaos.data.entity.response.Book
import com.sopt.aladinaos.databinding.ItemCartBodyBinding
import com.sopt.aladinaos.util.ItemDiffCallback

class CartAdapter(
    private val setCount: (Int) -> Int,
    private val plusOnClick: (Int) -> Unit,
    private val minusOnClick: (Int) -> Unit
) : ListAdapter<Book, CartAdapter.CartViewHolder>(
    cartDiffCallBack
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemCartBodyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CartViewHolder(binding, setCount, plusOnClick, minusOnClick)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class CartViewHolder(
        val binding: ItemCartBodyBinding,
        private val setCount: (Int) -> Int,
        private val plusOnClick: (Int) -> Unit,
        private val minusOnClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Book) {
            binding.data = data
            with(binding) {
                val count = setCount(absoluteAdapterPosition)
                tvCartBodyCount.text =
                    itemView.context.getString(R.string.cart_body_count, count)
                tvCartBodyMinus.setOnClickListener {
                    if (count in 2..9) {
                        minusOnClick(absoluteAdapterPosition)
                    }
                }
                tvCartBodyPlus.setOnClickListener {
                    if (count in 1..8) {
                        plusOnClick(absoluteAdapterPosition)
                    }
                }
            }
        }
    }

    companion object {
        private val cartDiffCallBack = ItemDiffCallback<Book>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
