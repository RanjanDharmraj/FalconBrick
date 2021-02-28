package com.dreamsunited.falconbrick.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dreamsunited.falconbrick.repository.Repository
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@ActivityScoped
class SplashViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _screenTransitionEvent = MutableLiveData<Unit>()

    val screenTransitionEvent: LiveData<Unit>
        get() = _screenTransitionEvent

    fun init() {
        repository
            .getAllData()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if(it.isNotEmpty()) {
                    _screenTransitionEvent.value = Unit
                } else {
                    saveData()
                }
            }
            .doOnError {
                print(it.message)
            }.subscribe()
    }

    private fun saveData() {
        repository
            .saveDataIntoDB()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                _screenTransitionEvent.value = Unit
            }
            .doOnError {
                print(it.message)
            }.subscribe()
    }
}