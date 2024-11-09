package com.wecanteen105.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.wecanteen105.core.database.dao.CastDao
import com.wecanteen105.core.database.dao.MovieDao
import com.wecanteen105.core.database.model.movie.MovieCastCrossRef
import com.wecanteen105.core.database.model.movie.MovieEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class MovieDaoTest {

    private lateinit var db: TmdtvDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var castDao: CastDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TmdtvDatabase::class.java).build()
        movieDao = db.movieDao()
        castDao = db.castDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun movieDao_test_upsertMovies_And_test_deleteMovies() = runTest {
        val movieEntities = listOf(
            testMovie(1, "Movie 1"),
            testMovie(2, "Movie 2"),
            testMovie(3, "Movie 3"),
        )
        movieDao.upsertMovies(movieEntities)

        val savedMovieEntities = movieDao.getOneOffMovieEntities()

        assertEquals(3, savedMovieEntities.size)


        movieDao.deleteMovies(setOf(1, 2))
        val remainingMovieEntity = movieDao.getOneOffMovieEntities().first()

        assertEquals(
            "Movie 3",
            remainingMovieEntity.title,
        )
        assertEquals(
            3,
            remainingMovieEntity.id,
        )
    }

    @Test
    fun movieDao_test_deleteMovies_deleteCrossRefEntities_and_castDao_deleteCasts() = runTest {
        save_MovieEntities_CastEntities_itsCrossRef_success()

        // to delete 1 movie and it's 2 casts.
        movieDao.deleteMovies(setOf(1))
        movieDao.deleteMovieCastCrossRefEntities(movieIds = setOf(1))
        castDao.deleteCasts(setOf(1,2))

        val remainingMovieEntities = movieDao.getOneOffMovieEntities()
        assertEquals(1, remainingMovieEntities.size)
        assertEquals("Movie 2", remainingMovieEntities.first().title)

        val remainingCastEntities = castDao.getOneOffCastEntities()
        assertEquals(2, remainingCastEntities.size)
        assertEquals(
            listOf(3,4),
            remainingCastEntities.map { it.id}
        )
    }

    @Test
    fun movieDao_test_getOneOffMovieWithCastEntities_is_correct() = runTest {
        save_MovieEntities_CastEntities_itsCrossRef_success()

        // verify 1 movie, 2 casts.
        val savedMovieWithCasts = movieDao.getOneOffMovieWithCastEntities(1).first()
        assertEquals(1, savedMovieWithCasts.movie.id)
        assertEquals(listOf(1,2), savedMovieWithCasts.casts.map{it.id})

    }

    private suspend fun save_MovieEntities_CastEntities_itsCrossRef_success() {
        val movieEntities = listOf(
            testMovie(1, "Movie 1"),
            testMovie(2, "Movie 2"),
        )
        val movieCasts = testCasts(listOf(1, 2, 3, 4))
        val movieCrossRefEntities = listOf(
            MovieCastCrossRef(1, 1),
            MovieCastCrossRef(1, 2),
            MovieCastCrossRef(2, 3),
            MovieCastCrossRef(2, 4),
        )

        // save 2 movies, 4 casts into db.
        movieDao.upsertMovies(movieEntities)
        castDao.insertOrIgnoreCasts(movieCasts)
        movieDao.insertOrIgnoreMovieCastCrossRefEntities(movieCrossRefEntities)

        // verify 2 movies, 4 casts.
        val savedMovieEntities = movieDao.getOneOffMovieEntities()
        val savedCastEntities = castDao.getOneOffCastEntities()
        assertEquals(2, savedMovieEntities.size)
        assertEquals(4, savedCastEntities.size)

    }

    private fun testMovie(id: Int, title: String) = MovieEntity(
        adult = false,
        backdropPath = "",
        id = id,
        overview = "overview",
        popularity = 9.0,
        posterPath = "",
        releaseDate = "",
        title = title,
        video = false,
        voteAverage = 8.0,
        voteCount = 1234,
    )
}

