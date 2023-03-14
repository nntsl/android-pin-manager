package com.nntsl.pinmanager.core.domain.usecase

import com.nntsl.pinmanager.core.domain.repository.PinCodesRepository
import com.nntsl.pinmanager.core.model.PinCode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedPinCodesUseCase @Inject constructor(
    private val pinCodesRepository: PinCodesRepository
) {
    operator fun invoke(): Flow<List<PinCode>> {
        return pinCodesRepository.getPinCodes()
    }
}
