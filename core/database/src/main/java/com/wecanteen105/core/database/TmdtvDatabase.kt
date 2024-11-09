package com.wecanteen105.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wecanteen105.core.database.dao.CastDao
import com.wecanteen105.core.database.dao.CastFtsDao
import com.wecanteen105.core.database.dao.CrewDao
import com.wecanteen105.core.database.dao.CrewFtsDao
import com.wecanteen105.core.database.dao.MovieDao
import com.wecanteen105.core.database.dao.MovieDetailDao
import com.wecanteen105.core.database.dao.MovieFtsDao
import com.wecanteen105.core.database.dao.RecentSearchQueryDao
import com.wecanteen105.core.database.dao.ReviewDao
import com.wecanteen105.core.database.dao.SeasonDao
import com.wecanteen105.core.database.dao.TvDao
import com.wecanteen105.core.database.dao.TvDetailDao
import com.wecanteen105.core.database.dao.TvFtsDao
import com.wecanteen105.core.database.dao.VideoDao
import com.wecanteen105.core.database.model.RecentSearchQueryEntity
import com.wecanteen105.core.database.model.ReviewEntity
import com.wecanteen105.core.database.model.SeasonEntity
import com.wecanteen105.core.database.model.VideoEntity
import com.wecanteen105.core.database.model.movie.MovieCastCrossRef
import com.wecanteen105.core.database.model.movie.MovieCrewCrossRef
import com.wecanteen105.core.database.model.movie.MovieDetailEntity
import com.wecanteen105.core.database.model.movie.MovieEntity
import com.wecanteen105.core.database.model.movie.MovieFtsEntity
import com.wecanteen105.core.database.model.people.CastEntity
import com.wecanteen105.core.database.model.people.CastFtsEntity
import com.wecanteen105.core.database.model.people.CrewEntity
import com.wecanteen105.core.database.model.people.CrewFtsEntity
import com.wecanteen105.core.database.model.tv.TvCastCrossRef
import com.wecanteen105.core.database.model.tv.TvCrewCrossRef
import com.wecanteen105.core.database.model.tv.TvDetailEntity
import com.wecanteen105.core.database.model.tv.TvEntity
import com.wecanteen105.core.database.model.tv.TvFtsEntity
import com.wecanteen105.core.database.util.InstantConverter
import com.wecanteen105.core.database.util.ListIntConverter

@Database(
    entities = [
        MovieEntity::class,
        MovieFtsEntity::class,
        MovieDetailEntity::class,
        MovieCastCrossRef::class,
        MovieCrewCrossRef::class,
        TvEntity::class,
        TvFtsEntity::class,
        TvDetailEntity::class,
        TvCastCrossRef::class,
        TvCrewCrossRef::class,
        SeasonEntity::class,
        CastEntity::class,
        CastFtsEntity::class,
        CrewEntity::class,
        CrewFtsEntity::class,
        ReviewEntity::class,
        VideoEntity::class,
        RecentSearchQueryEntity::class,
    ],
    version = 1,
    autoMigrations = [

    ],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
    ListIntConverter::class,
)
internal abstract class TmdtvDatabase : RoomDatabase() {
    abstract fun castDao(): CastDao
    abstract fun castFtsDao(): CastFtsDao
    abstract fun crewDao(): CrewDao
    abstract fun crewFtsDao(): CrewFtsDao
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun movieFtsDao(): MovieFtsDao
    abstract fun recentSearchQueryDao(): RecentSearchQueryDao
    abstract fun reviewDao(): ReviewDao
    abstract fun seasonDao(): SeasonDao
    abstract fun tvDao(): TvDao
    abstract fun tvDetailDao(): TvDetailDao
    abstract fun tvFtsDao(): TvFtsDao
    abstract fun videoDao(): VideoDao
}