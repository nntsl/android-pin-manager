package com.nntsl.pinmanager.core.testing.repository

import com.nntsl.pinmanager.core.domain.repository.PinCodesRepository
import com.nntsl.pinmanager.core.model.PinCode
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestPinCodesRepository : PinCodesRepository {

    private val _savedPinCodes =
        MutableSharedFlow<List<PinCode>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val currentSavedPinCodes get() = _savedPinCodes.replayCache.firstOrNull() ?: listOf()

    override val savedPinCodes: Flow<List<PinCode>> = _savedPinCodes

    override suspend fun savePinCode(name: String, code: String): Long {
        currentSavedPinCodes.let { current ->
            val id = current.maxOf { it.id } + 1
            val pinCodes = current + PinCode(name = name, code = code, id = id)

            _savedPinCodes.tryEmit(pinCodes)
            return id.toLong()
        }
    }

    override suspend fun deletePinCode(id: Int): Int {
        currentSavedPinCodes.let { current ->
            val pinCodes = current.toMutableList()
            pinCodes.removeIf { it.id == id }

            _savedPinCodes.tryEmit(pinCodes)
        }
        return id
    }

    /**
     * A test-only API to allow controlling the list of PIN codes from tests.
     */
    fun sendPinCodes(pinCodes: List<PinCode>) {
        _savedPinCodes.tryEmit(pinCodes)
    }
}
