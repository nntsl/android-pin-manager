package com.nntsl.pinmanager.feature.pin

import androidx.lifecycle.ViewModel
import com.nntsl.pinmanager.core.domain.usecase.GetSavedPinCodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val getSavedPinsUseCase: GetSavedPinCodesUseCase
) : ViewModel() {

}
