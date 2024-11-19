package com.josedev.colombiapp.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josedev.colombiapp.client.ClientImpl
import com.josedev.colombiapp.client.responses.toTouristAttractionState
import com.josedev.colombiapp.domain.repository.TouristicADetailEvent
import com.josedev.colombiapp.domain.state.StateState
import com.josedev.colombiapp.domain.state.StatusState
import com.josedev.colombiapp.domain.state.TouristAttractionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TouristicADetailVM @Inject constructor(
    private val repo: ClientImpl
): ViewModel(){

    private val _state = MutableStateFlow(TouristAttractionState())
    private val _status = MutableStateFlow(StatusState())

    val state = _state.asStateFlow()
    val status = _status.asStateFlow()

    fun onEvent(event: TouristicADetailEvent) {
        when (event) {
            is TouristicADetailEvent.TouristicADetail -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _status.value = _status.value.copy(isLoading = true)
                    val response = repo.touristicAttractionsById(event.id.toString())
                    if (response.message != null) {
                        _status.value = _status.value.copy(isLoading = false, isError = true, error = response.message)
                        return@launch
                    }
                    if (response.data != null) {
                        Log.d("DetailVM", response.data.toString())
                        _state.update { response.data.toTouristAttractionState() }
                    }
                    _status.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}