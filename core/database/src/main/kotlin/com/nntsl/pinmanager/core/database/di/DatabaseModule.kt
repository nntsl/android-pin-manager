package com.nntsl.pinmanager.core.database.di

import android.content.Context
import androidx.room.Room
import com.nntsl.pinmanager.core.database.PinManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesMyFinDatabase(
        @ApplicationContext context: Context,
    ): PinManagerDatabase = Room.databaseBuilder(
        context,
        PinManagerDatabase::class.java,
        "pin-manager-database"
    ).build()
}
