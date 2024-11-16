package com.josedev.colombiapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josedev.colombiapp.client.ClientImpl
import com.josedev.colombiapp.client.responses.toRegionState
import com.josedev.colombiapp.domain.repository.DepartmentsByRegionIdEvent
import com.josedev.colombiapp.domain.state.RegionState
import com.josedev.colombiapp.domain.state.StatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentsByRegionId @Inject constructor(
    private val repo: ClientImpl
): ViewModel() {

    private val _state = MutableStateFlow(RegionState())
    private val _status = MutableStateFlow(StatusState())

    val state = _state.asStateFlow()
    val status = _status.asStateFlow()

    fun onEvent(event: DepartmentsByRegionIdEvent) {
        when(event){
            is DepartmentsByRegionIdEvent.DepartmentsByIdRegion -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _status.update { it.copy(isLoading = true) }
                    val response = repo.statesByRegionId(event.id.toString())
                    if(response.message != null){
                        _status.update { it.copy(isLoading = false, isError = true, error = response.message) }
                        return@launch
                    }
                    if (response.data != null){
                        _state.update { response.data.toRegionState() }
                    }
                    _status.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}