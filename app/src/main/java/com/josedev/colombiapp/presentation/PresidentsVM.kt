package com.josedev.colombiapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josedev.colombiapp.client.ClientImpl
import com.josedev.colombiapp.client.responses.toPresidentState
import com.josedev.colombiapp.domain.repository.PresidentsEvent
import com.josedev.colombiapp.domain.state.PresidentState
import com.josedev.colombiapp.domain.state.StatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresidentsVM @Inject constructor(
    private val repo: ClientImpl
): ViewModel() {

    private val _state = MutableStateFlow(listOf(PresidentState()))
    private val _status = MutableStateFlow(StatusState())

    val state = _state.asStateFlow()
    val status = _status.asStateFlow()

    init {
        viewModelScope.launch {
            onEvent(PresidentsEvent.Presidents)
        }
    }

    fun onEvent(event: PresidentsEvent){
        when(event){
            PresidentsEvent.Presidents -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _status.update { it.copy(isLoading = true) }
                    val response = repo.listOfPresidents()
                    if (response.message != null){
                        _status.update { it.copy(isLoading = false, error = response.message, isError = true) }
                        return@launch
                    }
                    if(response.data != null){
                        Log.d("PresidentsVM", "onEvent: ${response.data}")
                        _state.update { response.data.map { it.toPresidentState() } }
                    }
                    _status.update { it.copy(isLoading = false, error = null, isError = false) }
                    Log.d("PresidentsVM", "onEvent: ${state.value}")
                }
            }
        }
    }

}