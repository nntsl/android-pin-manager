package com.nntsl.pinmanager.core.domain.usecase

import com.nntsl.pinmanager.core.testing.repository.TestPinCodesRepository
import com.nntsl.pinmanager.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class DeletePinCodeUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val pinCodesRepository = TestPinCodesRepository()

    val useCase = DeletePinCodeUseCase(pinCodesRepository)

    @Test
    fun whenSavePinCode_allPinCodesAreReturned() = runTest {

        pinCodesRepository.sendPinCodes(samplePinCodes)

        val deletedId = useCase(pinCodeToDelete.id)

        pinCodesRepository.deletePinCode(pinCodeToDelete.id)

        assertEquals(
            pinCodeToDelete.id,
            deletedId
        )
    }
}

