package com.josedev.colombiapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josedev.colombiapp.client.ClientImpl
import com.josedev.colombiapp.client.responses.Colombia
import com.josedev.colombiapp.client.responses.toColombiaState
import com.josedev.colombiapp.client.responses.toRegionState
import com.josedev.colombiapp.domain.repository.ColombiaEvent
import com.josedev.colombiapp.domain.state.ColombiaState
import com.josedev.colombiapp.domain.state.RegionState
import com.josedev.colombiapp.domain.state.StatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColombiaVM @Inject constructor(
    private val repo: ClientImpl
): ViewModel() {

    private var _state = MutableStateFlow(ColombiaState())

    var state = _state.asStateFlow()

    private val _status = MutableStateFlow(StatusState())
    val status = _status.asStateFlow()

    init {
        viewModelScope.launch {
            onEvent(ColombiaEvent.Colombia)
        }
    }

    fun onEvent(event: ColombiaEvent){
        when(event){
            ColombiaEvent.Colombia -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _status.update { it.copy(isLoading = true) }
                    val response = repo.colombia()
                    if(response.message != null){
                        _status.update { it.copy(isLoading = false, isError = true, error = response.message) }
                        return@launch
                    }
                    if(response.data != null){
                        val dataState = response.data.toColombiaState()
                        _state.update { dataState }
                    }
                    _status.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}