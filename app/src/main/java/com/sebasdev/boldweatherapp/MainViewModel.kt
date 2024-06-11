package com.sebasdev.boldweatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _isReadyState = MutableStateFlow(false)
    val isReady = _isReadyState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _isReadyState.value = true
        }
    }

}