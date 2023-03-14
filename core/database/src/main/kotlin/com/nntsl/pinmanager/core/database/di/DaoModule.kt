package com.nntsl.pinmanager.core.database.di

import com.nntsl.pinmanager.core.database.PinManagerDatabase
import com.nntsl.pinmanager.core.database.dao.PinCodesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providePinCodesDao(
        appDatabase: PinManagerDatabase
    ): PinCodesDao = appDatabase.pinCodesDao()
}
