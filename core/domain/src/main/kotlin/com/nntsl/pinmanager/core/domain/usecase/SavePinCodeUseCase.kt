package com.nntsl.pinmanager.core.domain.usecase

import com.nntsl.pinmanager.core.domain.repository.PinCodesRepository
import javax.inject.Inject

class SavePinCodeUseCase @Inject constructor(
    private val pinCodesRepository: PinCodesRepository
) {
    suspend operator fun invoke(name: String, code: String) {
        pinCodesRepository.savePinCode(name = name, code = code)
    }
}
