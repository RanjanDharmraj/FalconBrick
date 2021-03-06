package com.dreamsunited.falconbrick.ui.home

import android.view.MenuItem
import com.dreamsunited.falconbrick.databinding.ActivityFalconBinding
import com.dreamsunited.falconbrick.ui.BaseActivity
import com.dreamsunited.falconbrick.ui.home.viewmodel.FalconViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FalconActivity : BaseActivity<ActivityFalconBinding>() {

    @Inject
    lateinit var viewModel: FalconViewModel
    override fun inflate() = ActivityFalconBinding.inflate(layoutInflater)

    override fun onBindingCreated() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}