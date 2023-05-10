package com.example.recyclerviewmemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.example.recyclerviewmemo.databinding.ItemMemoBinding

class MemoAdapter(private val memos: ArrayList<Memo>): Adapter<MemoAdapter.MemoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoAdapter.MemoViewHolder {
        return MemoViewHolder(
            ItemMemoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
            false
            )
        )
    }

    override fun onBindViewHolder(holder: MemoAdapter.MemoViewHolder, position: Int) {
        holder.bind(memos[position])
        holder.binding.btnDelete.setOnClickListener {
            memos.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return memos.size
    }

    class MemoViewHolder(val binding: ItemMemoBinding)
        : ViewHolder(binding.root){
        fun bind(memo: Memo) {
            binding.tvMemo.text = memo.text
        }
    }
}