package com.wecanteen105.core.database.di

import android.content.Context
import androidx.room.Room
import com.wecanteen105.core.database.TmdtvDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesTmdtvDatabase(
        @ApplicationContext context:Context,
    ):TmdtvDatabase = Room.databaseBuilder(
        context,
        TmdtvDatabase::class.java,
        name = "tmdtv-db",
    ).build()
}