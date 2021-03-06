package com.dreamsunited.falconbrick.ui.home.adapter.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dreamsunited.falconbrick.databinding.ItemActivityBinding
import com.dreamsunited.falconbrick.ui.home.adapter.viewmodel.FalconActivityItemViewModel

class FalconActivityViewHolder(
    private val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(viewModel: FalconActivityItemViewModel) {
        (viewDataBinding as ItemActivityBinding).viewModel = viewModel
        viewDataBinding.executePendingBindings()
    }

}