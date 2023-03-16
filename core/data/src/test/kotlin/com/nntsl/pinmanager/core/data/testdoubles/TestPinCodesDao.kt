package com.nntsl.pinmanager.core.data.testdoubles

import com.nntsl.pinmanager.core.database.dao.PinCodesDao
import com.nntsl.pinmanager.core.database.model.PinCodeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestPinCodesDao : PinCodesDao {

    private var entitiesStateFlow = MutableStateFlow(
        listOf(
            PinCodeEntity(
                name = "1",
                code = "123456"
            ),
            PinCodeEntity(
                name = "2",
                code = "234567"
            )
        )
    )

    override fun getSavedPinCodes(): Flow<List<PinCodeEntity>> {
        return entitiesStateFlow
    }

    override suspend fun savePinCode(pinCode: PinCodeEntity): Long {
        entitiesStateFlow.update {
            it.plus(pinCode)
        }
        return pinCode.id.toLong()
    }

    override suspend fun deletePinCode(id: Int): Int {
        entitiesStateFlow.update { list ->
            list.filter { it.id != id }
        }
        return id
    }
}
