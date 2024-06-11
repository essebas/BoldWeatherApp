package com.sebasdev.boldweatherapp.forecast.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebasdev.boldweatherapp.R
import com.sebasdev.boldweatherapp.databinding.ItemLocationForecastDayBinding
import com.sebasdev.boldweatherapp.forecast.domain.models.ForecastDayModel
import com.sebasdev.boldweatherapp.forecast.presentation.adapter.diffutil.ForecastDayDiffUtil


class ForecastDayAdapter : RecyclerView.Adapter<ForecastDayAdapter.ForecastDayViewHolder>() {

    private var forecastDays = emptyList<ForecastDayModel>()

    inner class ForecastDayViewHolder(val binding: ItemLocationForecastDayBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDayViewHolder =
        ForecastDayViewHolder(
            ItemLocationForecastDayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = forecastDays.size

    override fun onBindViewHolder(holder: ForecastDayViewHolder, position: Int) {
        val forecastDay = forecastDays[position]
        val context = holder.itemView.context
        Glide.with(context)
            .load(forecastDay.conditionModel.icon)
            .centerCrop()
            .into(holder.binding.imvForecastDayIcon)
        holder.binding.apply {
            txvForecastDayTitle.text = forecastDay.date
            txvForecastDaySubtitle.text = context.getString(
                R.string.celsius_degrees_text,
                forecastDay.maxTemperatureInCelsius.toString()
            )
            txvForecastDayDescription.text = forecastDay.conditionModel.text
        }
    }

    fun injectData(newForecastList: List<ForecastDayModel>) {
        val diffUtil = ForecastDayDiffUtil(forecastDays, newForecastList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        forecastDays = newForecastList
        diffResult.dispatchUpdatesTo(this)
    }
}