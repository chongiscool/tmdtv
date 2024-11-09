package com.wecanteen105.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.wecanteen105.core.database.dao.MovieDetailDao
import com.wecanteen105.core.database.dao.ReviewDao
import com.wecanteen105.core.database.dao.VideoDao
import com.wecanteen105.core.database.model.movie.MovieDetailEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MovieDetailDaoTest {
    private lateinit var db: TmdtvDatabase
    private lateinit var movieDetailDao: MovieDetailDao
    private lateinit var reviewDao: ReviewDao
    private lateinit var videoDao: VideoDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            TmdtvDatabase::class.java
        ).build()

        movieDetailDao = db.movieDetailDao()
        reviewDao = db.reviewDao()
        videoDao = db.videoDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun test_insertOrIgnoreMovieDetails_success() = runTest {
        val entities = (1..3).map {
            testMovieDetail(it, "Movie Detail $it")
        }

        // Save entities into db
        val ids = movieDetailDao.insertOrIgnoreMovieDetails(entities)
        assertEquals(3, ids.size)
        assertEquals(3L, ids[2])

        // Verify entities are saved
        val savedMovieDetailEntities = movieDetailDao.getOneOffMovieDetailEntities()
        assertEquals(listOf(1, 2, 3), savedMovieDetailEntities.map { it.id })
        assertEquals("Movie Detail 3", savedMovieDetailEntities.last().title)

        // NOTE: double insert, ignore operation.
        // -1L return by every ignore
        val ids_again = movieDetailDao.insertOrIgnoreMovieDetails(entities)
        assertEquals((-1L).toList(3), ids_again)
    }

    @Test
    fun test_getOneOffMovieDetailWithReviewsById_And_deleteThem_success() = runTest {
        val entities = (1..2).map {
            testMovieDetail(it, "Movie Detail $it")
        }
        val reviewEntities = testReviews(
            listOf("1", "2", "3"),
            listOf(
                "Review Content 1",
                "Review Content 2",
                "Review Content 3",
            ),
            1
        )
        val reviewEntities2 = testReviews(
            listOf("4", "5", "6"),
            listOf(
                "Review Content 4",
                "Review Content 5",
                "Review Content 6",
            ),
            2
        )

        // Save all entities to db
        movieDetailDao.insertOrIgnoreMovieDetails(entities)
        reviewDao.insertOrIgnoreReviews(reviewEntities)
        reviewDao.insertOrIgnoreReviews(reviewEntities2)

        // Verify entities are saved
        val savedMovieDetailWithReviews =
            movieDetailDao.getOneOffMovieDetailWithReviewsById(1).first()
        assertEquals("Movie Detail 1", savedMovieDetailWithReviews.entity.title)
        val reviews = savedMovieDetailWithReviews.reviews
        assertEquals(3, reviews.size)
        assertEquals(listOf("1", "2", "3"), reviews.map { it.id })
        assertEquals("Review Content 3", reviews[2].content)

        // Delete one of movie detail and its reviews
        movieDetailDao.deleteMovieDetails(setOf(2))
        reviewDao.deleteReviews(setOf(2))
        reviewDao.deleteReviewByIds(setOf("1", "3"))

        // Verify the remaining entities
        val remainedMovieDetails = movieDetailDao.getOneOffMovieDetailEntities()
        val remainedReviews = reviewDao.getOneOffReviewEntities()
        assertEquals(
            listOf("Movie Detail 1"),
            remainedMovieDetails.map { it.title })
        assertEquals(listOf("2"), remainedReviews.map { it.id })
    }

    @Test
    fun test_getOneOffMovieDetailWithVideosById_And_deleteThem_success() = runTest {
        val entities = (1..2).map {
            testMovieDetail(it, "Movie Detail $it")
        }
        val videoEntities = testVideos(
            listOf("1", "2", "3"),
            listOf(
                "Movie Detail Trailer 1",
                "Movie Detail Trailer 2",
                "Movie Detail Trailer 3",
            ),
            1,
        )
        val videoEntities2 = testVideos(
            listOf("4", "5", "6"),
            listOf(
                "Movie Detail Trailer 4",
                "Movie Detail Trailer 5",
                "Movie Detail Trailer 6",
            ),
            2,
        )

        //  Save all entities to db
        movieDetailDao.insertOrIgnoreMovieDetails(entities)
        videoDao.insertOrIgnoreVideos(videoEntities)
        videoDao.insertOrIgnoreVideos(videoEntities2)

        // Verify entities are saved
        val savedMovieDetailWithVideos =
            movieDetailDao.getOneOffMovieDetailWithVideosById(listOf(1, 2))
        assertEquals(
            listOf("Movie Detail 1", "Movie Detail 2"),
            savedMovieDetailWithVideos.map { it.entity.title }
        )
        val savedVideos1 = savedMovieDetailWithVideos.first().videos
        val savedVideos2 = savedMovieDetailWithVideos.last().videos
        assertEquals(
            (1..3).map{"$it"},
            savedVideos1.map { it.id }
        )
        assertEquals(
            (4..6).map{"Movie Detail Trailer $it"},
            savedVideos2.map { it.name }
        )

        // Delete one of movie detail and its videos
        movieDetailDao.deleteMovieDetails(setOf(2))
        videoDao.deleteVideos(setOf(1))
        videoDao.deleteVideoByIds(setOf("4", "5"))

        // Verify the remaining entities
        val remainingVideoEntities = videoDao.getOneOffVideoEntities()
        assertEquals(listOf("6"), remainingVideoEntities.map{ it.id})
        val remainingDetailEntities = movieDetailDao.getOneOffMovieDetailEntities()
        assertEquals(listOf(1), remainingDetailEntities.map { it.id })

    }

    private fun testMovieDetail(id: Int, title: String) = MovieDetailEntity(
        adult = false,
        backdropPath = "backdropPath",
        belongsToCollection = "belongsToCollection",
        budget = 1,
        homepage = "homepage",
        id = id,
        imdbId = "imdbId",
        overview = "overview",
        popularity = 1.0,
        posterPath = "posterPath",
        releaseDate = "releaseDate",
        revenue = 100_000_000,
        runtime = 120,
        status = "status",
        tagline = "tagline",
        title = title,
        video = false,
        voteAverage = 1.0,
        voteCount = 1
    )

//    private fun testMovieDetails(ids:List<Int>, titles:List<String>): List<MovieDetailEntity> = ids.mapIndexed { index, id ->
//        MovieDetailEntity(
//            adult = false,
//            backdropPath = "backdropPath",
//            belongsToCollection = "belongsToCollection",
//            budget = 1,
//            homepage = "homepage",
//            id = id,
//            imdbId = "imdbId",
//            overview = "overview",
//            popularity = 1.0,
//            posterPath = "posterPath",
//            releaseDate = "releaseDate",
//            revenue = 100_000_000,
//            runtime = 120,
//            status = "status",
//            tagline = "tagline",
//            title = titles[index],
//            video = false,
//            voteAverage = 1.0,
//            voteCount = 1
//        )
//    }
}