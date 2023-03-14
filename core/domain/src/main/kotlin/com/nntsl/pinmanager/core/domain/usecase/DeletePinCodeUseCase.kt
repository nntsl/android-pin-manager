package com.nntsl.pinmanager.core.domain.usecase

import com.nntsl.pinmanager.core.domain.repository.PinCodesRepository
import javax.inject.Inject

class DeletePinCodeUseCase @Inject constructor(
    private val pinCodesRepository: PinCodesRepository
) {
    suspend operator fun invoke(id: Int) {
        pinCodesRepository.deletePinCode(id)
    }
}
