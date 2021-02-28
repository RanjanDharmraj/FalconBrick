package com.dreamsunited.falconbrick.ui.home

interface TabClick {
    fun onTabClick(position: Int)
}

interface OnKeyEnter {
    fun onDone()
    fun onBackPressed()
}