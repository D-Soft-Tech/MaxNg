package com.example.maxng.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.maxng.databinding.RecyclerViewItemBinding
import com.example.maxng.models.mapper.Domain

class RecyclerViewViewHolder(val itemBinding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(data: Domain) {
        itemBinding.domain = data
        itemBinding.executePendingBindings()
    }
}
