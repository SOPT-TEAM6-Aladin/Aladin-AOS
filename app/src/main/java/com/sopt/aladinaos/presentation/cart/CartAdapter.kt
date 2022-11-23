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
    private val minusOnClick: (Int) -> Unit,
    private val plusOnClick: (Int) -> Unit,
    private val updateCount: (Int, Int) -> Unit
) :
    ListAdapter<Book, CartAdapter.CartViewHolder>(
        cartDiffCallBack
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemCartBodyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CartViewHolder(binding, minusOnClick, plusOnClick, updateCount)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class CartViewHolder(
        val binding: ItemCartBodyBinding,
        private val minusOnClick: (Int) -> Unit,
        private val plusOnClick: (Int) -> Unit,
        private val updateCount: (Int, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Book) {
            binding.data = data
            with(binding) {
                tvCartBodyCount.text =
                    itemView.context.getString(R.string.cart_body_count, data.count)
                tvCartBodyMinus.setOnClickListener {
                    minusOnClick(absoluteAdapterPosition)
                    updateCount(absoluteAdapterPosition, data.count - 1)
                }
                tvCartBodyPlus.setOnClickListener {
                    plusOnClick(absoluteAdapterPosition)
                    updateCount(absoluteAdapterPosition, data.count + 1)
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
