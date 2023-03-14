package com.nntsl.pinmanager.core.domain.repository

import com.nntsl.pinmanager.core.model.PinCode
import kotlinx.coroutines.flow.Flow

interface PinCodesRepository {

    fun getPinCodes(): Flow<List<PinCode>>

    suspend fun savePinCode(name: String, code: String)

    suspend fun deletePinCode(id: Int)
}
