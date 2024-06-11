package com.sebasdev.boldweatherapp.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sebasdev.boldweatherapp.databinding.ItemSearchLocationBinding
import com.sebasdev.boldweatherapp.search.domain.models.SearchLocationModel
import com.sebasdev.boldweatherapp.search.presentation.adapter.diffutil.LocationsDiffUtil

class LocationsAdapter(val onLocationClick: (location: SearchLocationModel) -> Unit) :
    RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder>() {

    private var locations = emptyList<SearchLocationModel>()

    inner class LocationsViewHolder(val binding: ItemSearchLocationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder =
        LocationsViewHolder(
            ItemSearchLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val location = locations[position]
        holder.binding.apply {
            layoutLocation.setOnClickListener { onLocationClick(location) }
            txvLocationName.text = location.name
            txvLocationCountry.text = location.country
        }
    }

    fun injectData(newSearchedLocations: List<SearchLocationModel>) {
        val diffUtil = LocationsDiffUtil(locations, newSearchedLocations)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        locations = newSearchedLocations
        diffResult.dispatchUpdatesTo(this)
    }

}