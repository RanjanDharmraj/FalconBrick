package com.dreamsunited.falconbrick.ui.splash

import android.content.Intent
import com.dreamsunited.falconbrick.databinding.ActivitySplashBinding
import com.dreamsunited.falconbrick.ui.BaseActivity
import com.dreamsunited.falconbrick.ui.home.FalconActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    @Inject lateinit var viewModel: SplashViewModel

    override fun inflate() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onBindingCreated() {
        viewModel.init()
        viewModel.screenTransitionEvent.observe(this){
            startActivity(Intent(
                this, FalconActivity::class.java
            ))
            finish()
        }
    }
}