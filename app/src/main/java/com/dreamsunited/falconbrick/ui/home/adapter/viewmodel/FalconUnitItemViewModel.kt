package com.dreamsunited.falconbrick.ui.home.adapter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dreamsunited.falconbrick.model.Units
import com.dreamsunited.falconbrick.ui.home.adapter.FalconActivityAdapter

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