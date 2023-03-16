package com.nntsl.pinmanager.core.data.repository

import com.nntsl.pinmanager.core.database.dao.PinCodesDao
import com.nntsl.pinmanager.core.database.model.PinCodeEntity
import com.nntsl.pinmanager.core.database.model.asExternalModel
import com.nntsl.pinmanager.core.domain.repository.PinCodesRepository
import com.nntsl.pinmanager.core.model.PinCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PinCodesRepositoryImpl @Inject constructor(
    private val localDataSource: PinCodesDao
) : PinCodesRepository {

    override val savedPinCodes: Flow<List<PinCode>> =
        localDataSource.getSavedPinCodes()
            .map {
                it.map(PinCodeEntity::asExternalModel)
            }

    override suspend fun savePinCode(name: String, code: String): Long {
        return localDataSource.savePinCode(
            PinCodeEntity(
                name = name,
                code = code
            )
        )
    }

    override suspend fun deletePinCode(id: Int): Int {
        return localDataSource.deletePinCode(id)
    }
}
