package com.dreamsunited.falconbrick.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dreamsunited.falconbrick.databinding.ItemActivityBinding
import com.dreamsunited.falconbrick.model.Activities
import com.dreamsunited.falconbrick.ui.home.adapter.viewholder.FalconActivityViewHolder
import com.dreamsunited.falconbrick.ui.home.adapter.viewmodel.FalconActivityItemViewModel

class FalconActivityAdapter(
    private val floor: Int,
    private val list: List<Activities>
) : RecyclerView.Adapter<FalconActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FalconActivityViewHolder {
        return FalconActivityViewHolder(
            ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FalconActivityViewHolder, position: Int) {
        holder.bind(
            FalconActivityItemViewModel(
                floor,
                list[position]
            )
        )
    }

    override fun getItemCount(): Int = list.size

}