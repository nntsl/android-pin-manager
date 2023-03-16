package com.nntsl.pinmanager.core.domain.usecase

import com.nntsl.pinmanager.core.testing.repository.TestPinCodesRepository
import com.nntsl.pinmanager.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GetSavedPinCodesUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val pinCodesRepository = TestPinCodesRepository()

    val useCase = GetSavedPinCodesUseCase(pinCodesRepository)

    @Test
    fun whenGetPinCodes_allPinCodesAreReturned() = runTest {

        val pinCodes = useCase()

        pinCodesRepository.sendPinCodes(samplePinCodes)

        assertEquals(
            samplePinCodes,
            pinCodes.first()
        )
    }
}
