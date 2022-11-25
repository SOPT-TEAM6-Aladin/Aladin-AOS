package com.sopt.aladinaos.presentation.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.aladinaos.R
import com.sopt.aladinaos.data.entity.response.HomeData.Pick
import com.sopt.aladinaos.databinding.ItemHomeEditorChoiceBinding
import com.sopt.aladinaos.presentation.detail.DetailActivity

class HomeEditorChoiceAdapter(context: Context) :
    RecyclerView.Adapter<HomeEditorChoiceAdapter.HomeViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var editorChoiceList: List<Pick> = emptyList()

    class HomeViewHolder(
        private val binding: ItemHomeEditorChoiceBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(book: Pick) {
            Glide.with(this.binding.root)
                .load(book.thumbnail)
                .into(binding.ivHomeEditorBackground)
            binding.tvEditorTitle.text = itemView.context.getString(R.string.home_editor_name, book.name)
            binding.tvEditorAuthor.text = itemView.context.getString(R.string.home_editor_info, book.author, book.painter, book.publisher)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("id", book.bookId)
                startActivity(binding.root.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeEditorChoiceBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(editorChoiceList[position])
    }

    override fun getItemCount(): Int {
        return editorChoiceList.size
    }

    fun setPickList(bookList: List<Pick>) {
        this.editorChoiceList = bookList.toList()
        notifyDataSetChanged()
    }
}

// class HomeEditorChoiceAdapter : ListAdapter<Pick, HomeEditorChoiceAdapter.EditorChoiceViewHolder>(
//    editorChoiceDiffCallback
// ) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditorChoiceViewHolder {
//        val binding =
//            ItemHomeEditorChoiceBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        return EditorChoiceViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: EditorChoiceViewHolder, position: Int) {
//        holder.onBind(getItem(position))
//    }
//
//    class EditorChoiceViewHolder(
//        val binding: ItemHomeEditorChoiceBinding
//    ) :
//        RecyclerView.ViewHolder(binding.root) {
//        @SuppressLint("SetTextI18n")
//        fun onBind(book: Pick) {
//            Glide.with(this.binding.root)
//                .load(book.thumbnail)
//                .into(binding.ivHomeEditorBackground)
//            binding.tvEditorAuthor.text = "${book.author}지음, ${book.painter}그림 / ${book.publisher}"
//
//            binding.root.setOnClickListener {
//                val intent = Intent(binding.root.context, DetailActivity::class.java)
//                intent.putExtra("id", book.bookId)
//                startActivity(binding.root.context, intent, null)
//            }
//        }
//    }
//
//    companion object {
//        private val editorChoiceDiffCallback = ItemDiffCallback<Pick>(
//            onItemsTheSame = { old, new -> old.bookId == new.bookId },
//            onContentsTheSame = { old, new -> old == new }
//        )
//    }
// }
