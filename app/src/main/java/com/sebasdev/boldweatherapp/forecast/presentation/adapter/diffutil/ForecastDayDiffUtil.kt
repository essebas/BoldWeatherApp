package com.sebasdev.boldweatherapp.forecast.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastDayModel
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy

class ForecastDayDiffUtil(
    private val oldList: List<ForecastDayModel>,
    private val newList: List<ForecastDayModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].maxTemperatureInCelsius == newList[newItemPosition].maxTemperatureInCelsius

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = when {
        oldList[oldItemPosition].date != newList[newItemPosition].date -> false
        oldList[oldItemPosition].conditionModel.text != newList[newItemPosition].conditionModel.text -> false
        else -> true
    }
}