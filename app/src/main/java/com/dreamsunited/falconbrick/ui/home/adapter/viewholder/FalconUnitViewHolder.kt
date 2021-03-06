package com.dreamsunited.falconbrick.ui.home.adapter.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dreamsunited.falconbrick.databinding.ItemUnitBinding
import com.dreamsunited.falconbrick.ui.home.adapter.viewmodel.FalconUnitItemViewModel

class FalconUnitViewHolder(
    private val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(viewModel: FalconUnitItemViewModel) {
        (viewDataBinding as ItemUnitBinding).viewModel = viewModel
        viewDataBinding.executePendingBindings()
    }

}