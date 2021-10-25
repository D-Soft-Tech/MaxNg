package com.example.maxng.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.maxng.databinding.RecyclerViewItemBinding
import com.example.maxng.models.mapper.Domain

class StarWarsRecyclerViewAdapter(
    private val listener: LikeOnClick,
    private var starWars: List<Domain>
) : RecyclerView.Adapter<RecyclerViewViewHolder>() {
//    private var starWars: ArrayList<Domain> = arrayListOf()

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
        holder.itemBinding.imageView2.setOnClickListener {
            listener.favourite(it as ImageView, starWars[position])
        }
    }

    override fun getItemCount() = starWars.size

    @SuppressLint("NotifyDataSetChanged")
    fun notifyAdapterAfterFavouriting(newList: List<Domain>) {
        starWars = newList
        this.notifyDataSetChanged()
    }

    fun setStarWars(newList: List<Domain>) {
        val diff = StarWarsDiffUtil(starWars, newList)
        val diffResult = DiffUtil.calculateDiff(diff)
        starWars = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
