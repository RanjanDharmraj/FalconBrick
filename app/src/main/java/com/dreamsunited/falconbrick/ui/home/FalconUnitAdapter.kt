package com.dreamsunited.falconbrick.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.dreamsunited.falconbrick.databinding.ItemActivityBinding
import com.dreamsunited.falconbrick.databinding.ItemUnitBinding
import com.dreamsunited.falconbrick.model.Activities
import com.dreamsunited.falconbrick.model.Units

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

class FalconUnitViewHolder(
    private val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(viewModel: FalconUnitItemViewModel) {
        (viewDataBinding as ItemUnitBinding).viewModel = viewModel
        viewDataBinding.executePendingBindings()
    }

}

class FalconUnitItemViewModel(
    private val unit: Units
) : ViewModel() {
    val unitName = MutableLiveData<String>(unit.title)
    val adapter = MutableLiveData<FalconActivityAdapter>(
        FalconActivityAdapter(
            unit.floor!!,
            unit.activities!!
        )
    )
}

class FalconActivityViewHolder(
    private val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(viewModel: FalconActivityItemViewModel) {
        (viewDataBinding as ItemActivityBinding).viewModel = viewModel
        viewDataBinding.executePendingBindings()
    }

}

class FalconActivityItemViewModel(
    private val floor: Int,
    private val activities: Activities
) : ViewModel() {

    val stepName = MutableLiveData<String>(activities.stepName)
    val activityName = MutableLiveData<String>(activities.activityName)
    val progress = MutableLiveData<Int>(activities.progress)
    val progressText = MutableLiveData<String>()
    val floorNumber = MutableLiveData<String>()

    init {
        val value = activities.progress
        progressText.value = "$value%"
        floorNumber.value = "$floor D."
    }

}



