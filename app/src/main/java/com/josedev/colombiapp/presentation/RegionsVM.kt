package com.josedev.colombiapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josedev.colombiapp.client.ClientImpl
import com.josedev.colombiapp.client.responses.toRegionState
import com.josedev.colombiapp.domain.repository.RegionsEvent
import com.josedev.colombiapp.domain.state.RegionState
import com.josedev.colombiapp.domain.state.StatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegionsVM @Inject constructor(
    private val repo: ClientImpl
): ViewModel() {

    private val _state = MutableStateFlow(listOf(RegionState()))
    var state = _state.asStateFlow()

    private var _status = MutableStateFlow(StatusState())
    var status: StateFlow<StatusState> = _status.asStateFlow()

    init {
        viewModelScope.launch {
            onEvent(RegionsEvent.Regions)
        }
    }

    fun onEvent(event: RegionsEvent){
        when(event){
            RegionsEvent.Regions -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _status.update { it.copy(isLoading = true) }
                    val response = repo.region()
                    if (response.message != null){
                        _status.update { it.copy(isLoading = false, isError = true, error = response.message) }
                        return@launch
                    }
                    if (response.data != null){
                        _state.update { response.data.map { it.toRegionState() } }
                    }
                    _status.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}