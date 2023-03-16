package com.nntsl.pinmanager.core.data.repository

import com.nntsl.pinmanager.core.data.testdoubles.TestPinCodesDao
import com.nntsl.pinmanager.core.database.dao.PinCodesDao
import com.nntsl.pinmanager.core.database.model.PinCodeEntity
import com.nntsl.pinmanager.core.database.model.asExternalModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PinCodesRepositoryImplTest {

    private lateinit var subject: PinCodesRepositoryImpl

    private lateinit var pinCodesDao: PinCodesDao

    @Before
    fun setup() {
        pinCodesDao = TestPinCodesDao()

        subject = PinCodesRepositoryImpl(
            localDataSource = pinCodesDao
        )
    }

    @Test
    fun pinCodesRepository_pinCodesStream() = runTest {
        assertEquals(
            pinCodesDao.getSavedPinCodes().first().map(PinCodeEntity::asExternalModel),
            subject.savedPinCodes.first()
        )
    }

    @Test
    fun pinCodesRepository_saveItem() = runTest {
        val localPinCodeId = pinCodesDao.savePinCode(testPinCode)

        val savedPinCodeId = subject.savePinCode(name = testPinCode.name, code = testPinCode.code)

        assertEquals(
            localPinCodeId,
            savedPinCodeId
        )
    }

    @Test
    fun pinCodesRepository_deleteItem() = runTest {
        pinCodesDao.savePinCode(testPinCode)

        val localPinCodeId = pinCodesDao.deletePinCode(testPinCode.id)

        val deletedPinCodeId = subject.deletePinCode(testPinCode.id)

        assertEquals(
            localPinCodeId,
            deletedPinCodeId
        )
    }
}

val testPinCode = PinCodeEntity(
    name = "1",
    code = "123456"
)
