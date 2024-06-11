package com.sebasdev.boldweatherapp.search.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel

class LocationsDiffUtil(
    private val oldList: List<SearchLocationModel>,
    private val newList: List<SearchLocationModel>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        when {
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].country != newList[newItemPosition].country -> false
            else -> true
        }
}