package com.nntsl.pinmanager.feature.pincodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nntsl.pinmanager.core.domain.usecase.DeletePinCodeUseCase
import com.nntsl.pinmanager.core.domain.usecase.GetSavedPinCodesUseCase
import com.nntsl.pinmanager.core.model.PinCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    getSavedPinsUseCase: GetSavedPinCodesUseCase,
    private val deletePinCodeUseCase: DeletePinCodeUseCase
) : ViewModel() {

    val pinCodesUiState: StateFlow<PinCodesUiState> = getSavedPinsUseCase()
        .map<List<PinCode>, PinCodesUiState>(PinCodesUiState::Success)
        .onStart { emit(PinCodesUiState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PinCodesUiState.Loading
        )

    fun deletePinCode(id: Int) {
        viewModelScope.launch {
            deletePinCodeUseCase(id)
        }
    }
}

sealed interface PinCodesUiState {

    data class Success(
        val pinCodes: List<PinCode>
    ) : PinCodesUiState

    object Loading : PinCodesUiState
    object Error : PinCodesUiState
}
