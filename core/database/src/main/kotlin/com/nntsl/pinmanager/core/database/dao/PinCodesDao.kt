package com.nntsl.pinmanager.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nntsl.pinmanager.core.database.model.PinCodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PinCodesDao {

    @Query(value = "SELECT * FROM pin_codes")
    fun getSavedPinCodes(): Flow<List<PinCodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePinCode(pinCode: PinCodeEntity)

    @Query(value = "DELETE FROM pin_codes WHERE id = :id")
    suspend fun deletePinCode(id: Int)
}
