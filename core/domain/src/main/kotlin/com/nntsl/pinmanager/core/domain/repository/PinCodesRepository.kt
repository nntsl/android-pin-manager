package com.nntsl.pinmanager.core.domain.repository

import com.nntsl.pinmanager.core.model.PinCode
import kotlinx.coroutines.flow.Flow

interface PinCodesRepository {

    val savedPinCodes: Flow<List<PinCode>>

    suspend fun savePinCode(name: String, code: String): Long

    suspend fun deletePinCode(id: Int): Int
}
