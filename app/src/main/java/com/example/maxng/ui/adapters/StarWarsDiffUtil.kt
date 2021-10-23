package com.example.maxng.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.maxng.models.mapper.Domain

class StarWarsDiffUtil(
    private val oldStarWarList: List<Domain>,
    private val newStarWarList: List<Domain>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldStarWarList.size

    override fun getNewListSize() = newStarWarList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldStarWarList[oldItemPosition].name == newStarWarList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        when {
            oldStarWarList[oldItemPosition].name == newStarWarList[newItemPosition].name -> true
            oldStarWarList[oldItemPosition].liked == newStarWarList[newItemPosition].liked -> true
            oldStarWarList[oldItemPosition].category == newStarWarList[newItemPosition].category -> true
            oldStarWarList[oldItemPosition].image == newStarWarList[newItemPosition].image -> true
            oldStarWarList[oldItemPosition].releasedDate == newStarWarList[newItemPosition].releasedDate -> true
            else -> false
        }
}