package com.dreamsunited.falconbrick.ui.home

import androidx.lifecycle.ViewModel
import com.dreamsunited.falconbrick.repository.Repository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FalconViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun init() {

    }

}