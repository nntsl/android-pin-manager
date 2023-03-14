package com.nntsl.pinmanager.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nntsl.pinmanager.core.database.dao.PinCodesDao
import com.nntsl.pinmanager.core.database.model.PinCodeEntity

@Database(
    entities = [
        PinCodeEntity::class
    ],
    version = 1
)
abstract class PinManagerDatabase: RoomDatabase() {

    abstract fun pinCodesDao(): PinCodesDao
}