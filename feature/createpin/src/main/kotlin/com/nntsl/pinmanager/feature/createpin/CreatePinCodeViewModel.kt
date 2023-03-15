package com.nntsl.pinmanager.feature.createpin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nntsl.pinmanager.core.domain.usecase.GetSavedPinCodesUseCase
import com.nntsl.pinmanager.core.domain.usecase.SavePinCodeUseCase
import com.nntsl.pinmanager.feature.createpin.util.generatePinCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePinCodeViewModel @Inject constructor(
    getSavedPinsUseCase: GetSavedPinCodesUseCase,
    private val savePinCodeUseCase: SavePinCodeUseCase
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        CreatePinCodeViewModelState()
    )

    private val currentName = MutableStateFlow("")

    private val savedPinCodes: Flow<List<String>> = getSavedPinsUseCase()
        .map {
            it.map { item -> item.name }
        }

    val createPinCodeUiState: StateFlow<CreatePinCodeUiState> =
        combine(
            savedPinCodes,
            currentName,
            viewModelState
        ) { savedNames, currentName, viewModelState ->
            toUiState(savedNames, currentName, viewModelState)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = CreatePinCodeUiState.Loading
            )

    init {
        setPinCode()
    }

    private fun toUiState(
        savedNames: List<String>,
        currentName: String,
        viewModelState: CreatePinCodeViewModelState
    ): CreatePinCodeUiState {
        return if (viewModelState.saved) {
            CreatePinCodeUiState.Saved
        } else {
            val nameExist = savedNames.contains(currentName)
            CreatePinCodeUiState.Data(
                generatedPinCode = viewModelState.generatedPinCode,
                name = currentName,
                enableCreateButton = currentName.isNotEmpty() && !nameExist,
                error = if (nameExist) R.string.pin_name_already_exist else null,
            )
        }
    }

    fun updatePinName(input: String) {
        currentName.value = input
    }

    fun createPinCode(name: String) {
        viewModelState.update {
            it.copy(saved = true)
        }
        viewModelScope.launch {
            savePinCodeUseCase(name = name, code = viewModelState.value.generatedPinCode)
        }
    }

    private fun setPinCode() {
        val pin = generatePinCode()
        viewModelState.update {
            it.copy(generatedPinCode = pin)
        }
    }
}

sealed interface CreatePinCodeUiState {

    object Loading : CreatePinCodeUiState

    data class Data(
        val enableCreateButton: Boolean,
        val generatedPinCode: String,
        val name: String,
        val error: Int?
    ) : CreatePinCodeUiState

    object Saved : CreatePinCodeUiState
}

private data class CreatePinCodeViewModelState(
    val generatedPinCode: String = "",
    val saved: Boolean = false
)
