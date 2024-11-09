package com.wecanteen105.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.wecanteen105.core.database.dao.ReviewDao
import com.wecanteen105.core.database.dao.SeasonDao
import com.wecanteen105.core.database.dao.TvDetailDao
import com.wecanteen105.core.database.dao.VideoDao
import com.wecanteen105.core.database.model.ReviewEntity
import com.wecanteen105.core.database.model.SeasonEntity
import com.wecanteen105.core.database.model.VideoEntity
import com.wecanteen105.core.database.model.tv.TvDetailEntity
import com.wecanteen105.core.model.AuthorDetails
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class TvDetailDaoTest {

    private lateinit var db: TmdtvDatabase
    private lateinit var tvDetailDao: TvDetailDao
    private lateinit var seasonDao: SeasonDao
    private lateinit var reviewDao: ReviewDao
    private lateinit var videoDao: VideoDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            TmdtvDatabase::class.java
        ).build()

        tvDetailDao = db.tvDetailDao()
        seasonDao = db.seasonDao()
        reviewDao = db.reviewDao()
        videoDao = db.videoDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun tvDetailDao_insertTvDetail_success() = runTest {
        val entities = (1..3).map {
            testTvDetail(it, "Tv Series $it")
        }

        // Save entities into db.
        val ids = tvDetailDao.insertOrIgnoreTvDetails(entities)
        assertEquals(3, ids.size)
        assertEquals(3L, ids[2])

        // Verify entities are saved
        val savedTvDetailEntities = tvDetailDao.getOneOffTvDetailEntities()
        assertEquals(listOf(1,2,3), savedTvDetailEntities.map { it.id })
        assertEquals("Tv Series 3", savedTvDetailEntities.last().name)

        // NOTE: double insert, ignore operation.
        // -1L return by every ignore
        val ids_again = tvDetailDao.insertOrIgnoreTvDetails(entities)
        assertEquals(listOf(-1L, -1L, -1L), ids_again)

    }

    @Test
    fun test_getOneOffTvDetailWithSeasonsById_deleteThem_success() = runTest {
        val entities = (1..2).map {
            testTvDetail(it, "Tv Series $it")
        }
        val seasonEntities = testSeasons(
            listOf(1, 2, 3),
            listOf(
                "Tv Series 1 Season 1",
                "Tv Series 1 Season 2",
                "Tv Series 1 Season 3",
            ),
            1
        )
        val seasonEntities2 = testSeasons(
            listOf(4, 5, 6),
            listOf(
                "Tv Series 2 Season 1",
                "Tv Series 2 Season 2",
                "Tv Series 2 Season 3",
            ),
            2
        )

        // save all entities into db
        tvDetailDao.insertOrIgnoreTvDetails(entities)
        seasonDao.insertOrIgnoreSeasons(seasonEntities)
        seasonDao.insertOrIgnoreSeasons(seasonEntities2)

        // verify all entities are saved
        val savedTvDetailWithSeasons2 = tvDetailDao.getOneOffTvDetailWithSeasonsById(2).first()
        assertEquals("Tv Series 2", savedTvDetailWithSeasons2.entity.name)
        val seasons = savedTvDetailWithSeasons2.seasons
        assertEquals(3, seasons.size)
        assertEquals(listOf(4, 5, 6), seasons.map { it.id })
        assertEquals("Tv Series 2 Season 3", seasons[2].name)

        // delete them all
        tvDetailDao.deleteTvDetails(setOf(1,2))
        seasonDao.deleteSeasons(setOf(1))
        seasonDao.deleteSeasonByIds((1  ..5).toSet())

        // verify they are deleted
        val lastSeasonEntity = seasonDao.getOneOffSeasonEntities().first()
        assertEquals(6, lastSeasonEntity.id)

    }

    @Test
    fun test_getOneOffTvDetailWithReviewsById_deleteThem_success() = runTest {
        val entities = (1..2).map {
            testTvDetail(it, "Tv Series $it")
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
            2,
        )

        // Saved entities into db
        tvDetailDao.insertOrIgnoreTvDetails(entities)
        reviewDao.insertOrIgnoreReviews(reviewEntities)
        reviewDao.insertOrIgnoreReviews(reviewEntities2)

        // Verify entities are saved
        val savedTvDetailWithReviews = tvDetailDao.getOneOffTvDetailWithReviewsById(1).first()
        assertEquals("Tv Series 1", savedTvDetailWithReviews.entity.name)

        val reviews = savedTvDetailWithReviews.reviews
        assertEquals(3, reviews.size)
        assertEquals(listOf("1", "2", "3"), reviews.map { it.id })
        assertEquals("Review Content 3", reviews[2].content)

        // Delete one of tv detail and its reviews
        tvDetailDao.deleteTvDetails(setOf(1,2))
        reviewDao.deleteReviews(setOf(2))
        reviewDao.deleteReviewByIds(setOf("2", "3"))

        // Verify they are deleted
        val lastReviewEntity = reviewDao.getOneOffReviewEntities().first()
        assertEquals("1", lastReviewEntity.id)
    }

    @Test
    fun test_getOneOffTvDetailWithVideosById_deleteThem_success() = runTest {
        val entities = (1..2).map {
            testTvDetail(it, "Tv Series $it")
        }
        val videoEntities = testVideos(
            listOf("1", "2", "3"),
            listOf(
                "Tv Series Trailer 1",
                "Tv Series Trailer 2",
                "Tv Series Trailer 3",
            ),
            1
        )
        val videoEntities2 = testVideos(
            listOf("4", "5", "6"),
            listOf(
                "Tv Series Trailer 4",
                "Tv Series Trailer 5",
                "Tv Series Trailer 6",
            ),
            2
        )

        // Saved entities into db
        tvDetailDao.insertOrIgnoreTvDetails(entities)
        videoDao.insertOrIgnoreVideos(videoEntities)
        videoDao.insertOrIgnoreVideos(videoEntities2)

        // Verify entities are saved
        val savedTvDetailWithVideos = tvDetailDao.getOneOffTvDetailWithVideosById(1).first()
        assertEquals("Tv Series 1", savedTvDetailWithVideos.entity.name)
        val savedVideos = savedTvDetailWithVideos.videos
        assertEquals(3, savedVideos.size)
        assertEquals(listOf("1", "2", "3"), savedVideos.map { it.id })
        assertEquals("Tv Series Trailer 3", savedVideos[2].name)

        // Delete one of tv detail and its videos
        tvDetailDao.deleteTvDetails(setOf(2))
        videoDao.deleteVideos(setOf(2))
        videoDao.deleteVideoByIds(setOf("1","2"))

        // Verify they are deleted
        val lastTvDetailEntity = tvDetailDao.getOneOffTvDetailEntities().first()
        assertEquals(1, lastTvDetailEntity.id)
        val lastVideoEntity = videoDao.getOneOffVideoEntities().first()
        assertEquals("3", lastVideoEntity.id)
    }

    private fun testSeasons(ids: List<Int>, names: List<String>, tvDetailId: Int) =
        ids.mapIndexed { index, id ->
            SeasonEntity(
                airDate = "",
                episodeCount = 1,
                id = id,
                name = names[index],
                overview = "",
                posterPath = "posterPath",
                seasonNumber = 8,
                voteAverage = 8.0,
                tvDetailId = tvDetailId,
            )
        }

    private fun testTvDetail(id: Int, name: String) = TvDetailEntity(
        adult = false,
        backdropPath = "",
        episodeRunTime = emptyList(),
        firstAirDate = "",
        homepage = "homepage",
        id = id,
        inProduction = true,
        lastAirDate = "",
        name = name,
        nextEpisodeToAir = "",
        numberOfEpisodes = 8,
        numberOfSeasons = 2,
        overview = "",
        popularity = 1.0,
        posterPath = "",
        status = "status",
        tagline = "tagline",
        type = "",
        voteAverage = 8.0,
        voteCount = 0,
    )
}