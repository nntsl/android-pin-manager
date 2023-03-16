package com.nntsl.pinmanager.feature.pincodes

import com.nntsl.pinmanager.core.domain.usecase.DeletePinCodeUseCase
import com.nntsl.pinmanager.core.domain.usecase.GetSavedPinCodesUseCase
import com.nntsl.pinmanager.core.testing.repository.TestPinCodesRepository
import com.nntsl.pinmanager.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class PinCodesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val pinCodesRepository = TestPinCodesRepository()

    private val getSavedPinCodesUseCase = GetSavedPinCodesUseCase(
        pinCodesRepository = pinCodesRepository
    )

    private val deletePinCodeUseCase = DeletePinCodeUseCase(
        pinCodesRepository = pinCodesRepository
    )

    private lateinit var viewModel: PinViewModel

    @Before
    fun setUp() {
        viewModel = PinViewModel(
            getSavedPinCodesUseCase,
            deletePinCodeUseCase = deletePinCodeUseCase
        )
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        assertEquals(
            PinCodesUiState.Loading,
            viewModel.pinCodesUiState.value
        )
    }

    @Test
    fun uiState_whenPinCodesLoaded_thenShowSuccess() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.pinCodesUiState.collect() }

        pinCodesRepository.sendPinCodes(testInputPinCodes)

        assertEquals(
            PinCodesUiState.Success(testInputPinCodes),
            viewModel.pinCodesUiState.value
        )

        collectJob1.cancel()
    }

    @Test
    fun uiState_whenAddPinCode_thenShowUpdatedList() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.pinCodesUiState.collect() }

        pinCodesRepository.sendPinCodes(testInputPinCodes)
        pinCodesRepository.savePinCode(name = testInputNewPinCode.name, code = testInputNewPinCode.code)

        val newList = testInputPinCodes.toMutableList()
        newList.add(testInputNewPinCode)

        assertEquals(
            PinCodesUiState.Success(newList),
            viewModel.pinCodesUiState.value
        )

        collectJob1.cancel()
    }

    @Test
    fun uiState_whenDeletePinCode_thenShowUpdatedList() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.pinCodesUiState.collect() }

        pinCodesRepository.sendPinCodes(testInputPinCodes)
        pinCodesRepository.deletePinCode(3)

        val newList = testInputPinCodes.toMutableList()
        newList.removeLast()

        assertEquals(
            PinCodesUiState.Success(newList),
            viewModel.pinCodesUiState.value
        )

        collectJob1.cancel()
    }
}
