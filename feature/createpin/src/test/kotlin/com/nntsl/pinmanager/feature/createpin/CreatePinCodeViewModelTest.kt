package com.nntsl.pinmanager.feature.createpin

import com.nntsl.pinmanager.core.domain.usecase.GetSavedPinCodesUseCase
import com.nntsl.pinmanager.core.domain.usecase.SavePinCodeUseCase
import com.nntsl.pinmanager.core.model.PinCode
import com.nntsl.pinmanager.core.testing.repository.TestPinCodesRepository
import com.nntsl.pinmanager.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class CreatePinCodeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val pinCodesRepository = TestPinCodesRepository()

    private val getSavedPinCodesUseCase = GetSavedPinCodesUseCase(
        pinCodesRepository = pinCodesRepository
    )

    private val savePinCodeUseCase = SavePinCodeUseCase(
        pinCodesRepository = pinCodesRepository
    )

    private lateinit var viewModel: CreatePinCodeViewModel

    @Before
    fun setUp() {
        viewModel = CreatePinCodeViewModel(
            getSavedPinCodesUseCase,
            savePinCodeUseCase = savePinCodeUseCase
        )
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        assertEquals(
            CreatePinCodeUiState.Loading,
            viewModel.createPinCodeUiState.value
        )
    }

    @Test
    fun uiState_whenLoaded_thenShowData() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.createPinCodeUiState.collect() }
        val collectJob2 = launch(UnconfinedTestDispatcher()) { viewModel.viewModelState.collect() }

        pinCodesRepository.sendPinCodes(testInputPinCodes)
        viewModel.setPinCode()

        assertEquals(
            CreatePinCodeUiState.Data(
                name = "",
                enableCreateButton = false,
                generatedPinCode = viewModel.viewModelState.value.generatedPinCode,
                isErrorName = false
            ),
            viewModel.createPinCodeUiState.value
        )

        collectJob1.cancel()
        collectJob2.cancel()
    }

    @Test
    fun uiState_whenInputNotExistedName_thenShowCorrectData() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.createPinCodeUiState.collect() }
        val collectJob2 = launch(UnconfinedTestDispatcher()) { viewModel.viewModelState.collect() }

        pinCodesRepository.sendPinCodes(testInputPinCodes)
        viewModel.updatePinName("4")

        assertEquals(
            CreatePinCodeUiState.Data(
                name = "4",
                enableCreateButton = true,
                generatedPinCode = viewModel.viewModelState.value.generatedPinCode,
                isErrorName = false
            ),
            viewModel.createPinCodeUiState.value
        )

        collectJob1.cancel()
        collectJob2.cancel()
    }

    @Test
    fun uiState_whenInputExistedName_thenShowErrorData() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.createPinCodeUiState.collect() }
        val collectJob2 = launch(UnconfinedTestDispatcher()) { viewModel.viewModelState.collect() }

        pinCodesRepository.sendPinCodes(testInputPinCodes)
        viewModel.updatePinName("1")

        assertEquals(
            CreatePinCodeUiState.Data(
                name = "1",
                enableCreateButton = false,
                generatedPinCode = viewModel.viewModelState.value.generatedPinCode,
                isErrorName = true
            ),
            viewModel.createPinCodeUiState.value
        )

        collectJob1.cancel()
        collectJob2.cancel()
    }

    @Test
    fun uiState_whenSaveNewPinCode_thenShowSavedState() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) { viewModel.createPinCodeUiState.collect() }
        val collectJob2 = launch(UnconfinedTestDispatcher()) { viewModel.viewModelState.collect() }

        pinCodesRepository.sendPinCodes(testInputPinCodes)
        viewModel.updatePinName("4")

        assertEquals(
            CreatePinCodeUiState.Data(
                name = "4",
                enableCreateButton = true,
                generatedPinCode = viewModel.viewModelState.value.generatedPinCode,
                isErrorName = false
            ),
            viewModel.createPinCodeUiState.value
        )

        viewModel.createPinCode("4")

        assertEquals(
            CreatePinCodeUiState.Saved,
            viewModel.createPinCodeUiState.value
        )

        collectJob1.cancel()
        collectJob2.cancel()
    }
}

private val testInputPinCodes = listOf(
    PinCode(
        id = 1,
        name = "1",
        code = "123456"
    ),
    PinCode(
        id = 2,
        name = "2",
        code = "234567"
    ),
    PinCode(
        id = 3,
        name = "3",
        code = "345678"
    )
)
