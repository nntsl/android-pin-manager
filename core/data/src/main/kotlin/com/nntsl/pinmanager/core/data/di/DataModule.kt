package com.nntsl.pinmanager.core.data.di

import com.nntsl.pinmanager.core.data.repository.PinCodesRepositoryImpl
import com.nntsl.pinmanager.core.domain.repository.PinCodesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsPinCodesRepository(
        pinCodesRepository: PinCodesRepositoryImpl
    ): PinCodesRepository
}
