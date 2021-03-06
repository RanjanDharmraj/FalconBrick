package com.dreamsunited.falconbrick.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dreamsunited.falconbrick.databinding.ItemUnitBinding
import com.dreamsunited.falconbrick.model.Units
import com.dreamsunited.falconbrick.ui.home.adapter.viewholder.FalconUnitViewHolder
import com.dreamsunited.falconbrick.ui.home.adapter.viewmodel.FalconUnitItemViewModel

class FalconUnitAdapter(
    private val list: List<Units>
) : RecyclerView.Adapter<FalconUnitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FalconUnitViewHolder {
        return FalconUnitViewHolder(
            ItemUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FalconUnitViewHolder, position: Int) {
        holder.bind(
            FalconUnitItemViewModel(list[position])
        )
    }

    override fun getItemCount(): Int = list.size

}