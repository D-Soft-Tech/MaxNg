package com.example.maxng.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.maxng.databinding.RecyclerViewItemBinding
import com.example.maxng.models.mapper.Domain

class StarWarsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewViewHolder>() {
    private var starWars: List<Domain> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder =
        RecyclerViewViewHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.bind(starWars[position])
    }

    override fun getItemCount() = starWars.size

    fun setStarWars(newList: List<Domain>) {
        val difUtil = StarWarsDiffUtil(starWars, newList)
        val diffResult = DiffUtil.calculateDiff(difUtil)
        starWars = newList
        diffResult.dispatchUpdatesTo(this)
    }
}