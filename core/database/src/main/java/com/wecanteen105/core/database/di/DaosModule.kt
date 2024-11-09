package com.wecanteen105.core.database.di

import com.wecanteen105.core.database.TmdtvDatabase
import com.wecanteen105.core.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesCastDao(
        database: TmdtvDatabase,
    ): CastDao = database.castDao()

    fun providesCastFtsDao(
        database: TmdtvDatabase
    ):CastFtsDao = database.castFtsDao()

    @Provides
    fun providesCrewDao(
        database: TmdtvDatabase,
    ): CrewDao = database.crewDao()

    @Provides
    fun providesCrewFtsDao(
        database: TmdtvDatabase,
    ): CrewFtsDao = database.crewFtsDao()

    @Provides
    fun providesMovieDao(
        database: TmdtvDatabase
    ):MovieDao = database.movieDao()

    @Provides
    fun providesMovieDetailDao(
        database: TmdtvDatabase
    ):MovieDetailDao = database.movieDetailDao()

    @Provides
    fun providesMovieFtsDao(
        database: TmdtvDatabase
    ):MovieFtsDao = database.movieFtsDao()

    @Provides
    fun providesRecentSearchQueryDao(
        database: TmdtvDatabase,
    ):RecentSearchQueryDao = database.recentSearchQueryDao()

    @Provides
    fun providesReviewDao(
        database: TmdtvDatabase,
    ):ReviewDao = database.reviewDao()

    @Provides
    fun providesSeasonDao(
        database: TmdtvDatabase,
    ):SeasonDao = database.seasonDao()

    @Provides
    fun providesTvDao(
        database: TmdtvDatabase,
    ):TvDao = database.tvDao()

    @Provides
    fun providesTvDetailDao(
        database: TmdtvDatabase,
    ): TvDetailDao = database.tvDetailDao()

    @Provides
    fun providesTvFtsDao(
        database: TmdtvDatabase,
    ):TvFtsDao = database.tvFtsDao()

    @Provides
    fun providesVideoDao(
        database: TmdtvDatabase,
    ):VideoDao = database.videoDao()
}