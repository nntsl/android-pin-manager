package com.nntsl.pinmanager.core.domain.usecase

import com.nntsl.pinmanager.core.testing.repository.TestPinCodesRepository
import com.nntsl.pinmanager.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SavePinCodeUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val pinCodesRepository = TestPinCodesRepository()

    val useCase = SavePinCodeUseCase(pinCodesRepository)

    @Test
    fun whenSavePinCode_allPinCodesAreReturned() = runTest {

        pinCodesRepository.sendPinCodes(samplePinCodes)

        val savedId = useCase(name = pinCodeToSave.name, code = pinCodeToSave.code)

        pinCodesRepository.savePinCode(name = pinCodeToSave.name, code = pinCodeToSave.code)

        assertEquals(
            pinCodeToSave.id.toLong(),
            savedId
        )
    }
}
