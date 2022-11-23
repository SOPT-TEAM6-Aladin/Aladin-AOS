package com.sopt.aladinaos.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.aladinaos.databinding.ItemHomeEditorChoiceBinding

class HomeEditorChoiceAdapter(context: Context) :
    RecyclerView.Adapter<HomeEditorChoiceAdapter.HomeViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var editorChoiceList: List<String> = emptyList() // 다시 수정

    class HomeViewHolder(
        private val binding: ItemHomeEditorChoiceBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeEditorChoiceBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
//        holder.onBind(editorChoiceList[position])
    }

    override fun getItemCount(): Int {
        return editorChoiceList.size
    }

    fun setRepoList(bookList: List<String>) { // 나중에 제네릭 수정
        this.editorChoiceList = bookList.toList()
        notifyDataSetChanged()
    }
}
