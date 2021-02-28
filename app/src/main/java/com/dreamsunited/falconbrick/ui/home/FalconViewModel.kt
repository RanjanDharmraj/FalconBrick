package com.dreamsunited.falconbrick.ui.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.dreamsunited.falconbrick.model.BlockData
import com.dreamsunited.falconbrick.model.Units
import com.dreamsunited.falconbrick.repository.Repository
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@ActivityScoped
class FalconViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val tabItems = MutableLiveData<List<String>>()

    val searchText = MutableLiveData<String>()
    val emptySearchFullText = MutableLiveData<String>()
    val emptySearchText = MutableLiveData<String>()

    val adapter = MutableLiveData<FalconUnitAdapter>(FalconUnitAdapter(mutableListOf()))

    val mainContainerVisibility = MutableLiveData<Int>(View.GONE)
    val emptyContainerVisibility = MutableLiveData<Int>(View.VISIBLE)
    val emptySearchContainerVisibility = MutableLiveData<Int>(View.GONE)

    private var isFirstTime = true

    private var list : List<BlockData> ?= null

    val tabClick = object : TabClick {
        override fun onTabClick(position: Int) {
            when(position) {
                0 -> {
                    val units = mutableListOf<Units>()
                    list?.forEach {
                        it.units?.let { unitList ->
                            units.addAll(unitList)
                        }
                    }
                    loadAdapter(units)
                }
                else -> {
                    list?.let {
                        loadAdapter(it[position-1].units!!)
                    }
                }
            }
        }
    }

    val searchDone = object : OnKeyEnter {
        override fun onDone() {
            searchText.value?.let {
                isFirstTime = false
                fetchSearchedList(it)
            }
        }

        override fun onBackPressed() {
            if(searchText.value.isNullOrEmpty()) {
                init()
            }
        }
    }

    private fun fetchSearchedList(searchedText: String) {
        repository.getDataOnSearch(searchedText)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if(it.isNotEmpty()) {
                    list = it
                    populateTabs(it)
                    toggleView(false)
                } else {
                    toggleView(true)
                }
            }
            .doOnError {
                print(it.message)
                toggleView(true)
            }
            .subscribe()
    }

    private fun toggleView(isToggle: Boolean) {
        if(!isToggle) {
            mainContainerVisibility.value = View.VISIBLE
            emptyContainerVisibility.value = View.GONE
            emptySearchContainerVisibility.value = View.GONE
        } else {
            val text = searchText.value
            mainContainerVisibility.value = View.GONE
            emptyContainerVisibility.value = View.GONE
            emptySearchContainerVisibility.value = View.VISIBLE
            emptySearchFullText.postValue(
                "Term $text not found"
            )
            emptySearchText.postValue(
                text
            )
        }
    }

    fun init() {
        if(!isFirstTime) fetchAllData()
    }

    private fun fetchAllData() {
        repository.getAllData()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if(it.isNotEmpty()) {
                    list = it
                    populateTabs(it)
                    toggleView(false)
                } else {
                    toggleView(true)
                }
            }
            .doOnError {
                print(it.message)
                toggleView(true)
            }
            .subscribe()
    }

    private fun populateTabs(list: List<BlockData>) {
        val tabItemList = mutableListOf("ALL")
        list.forEach {
            tabItemList.add(it.blockName!!)
        }
        tabItems.postValue(tabItemList)
    }

    private fun loadAdapter(unitList: List<Units>) {
        adapter.postValue(
            FalconUnitAdapter(unitList)
        )
    }

}