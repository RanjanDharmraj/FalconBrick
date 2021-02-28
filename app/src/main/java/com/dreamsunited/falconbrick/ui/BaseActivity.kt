package com.dreamsunited.falconbrick.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<BindingType: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: BindingType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate()
        onBindingCreated()
        setContentView(binding.root)
    }

    abstract fun inflate(): BindingType

    abstract fun onBindingCreated()
}