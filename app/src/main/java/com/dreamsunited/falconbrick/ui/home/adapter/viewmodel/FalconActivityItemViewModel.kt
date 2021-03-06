package com.dreamsunited.falconbrick.ui.home.adapter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dreamsunited.falconbrick.model.Activities

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