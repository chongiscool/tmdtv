package com.wecanteen105.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.wecanteen105.core.database.dao.CastDao
import com.wecanteen105.core.database.dao.CrewDao
import com.wecanteen105.core.database.dao.TvDao
import com.wecanteen105.core.database.model.tv.TvEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class TvDaoTest {

    private lateinit var db: TmdtvDatabase
    private lateinit var tvDao: TvDao
    private lateinit var castDao: CastDao
    private lateinit var crewDao: CrewDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TmdtvDatabase::class.java).build()
        tvDao = db.tvDao()
        castDao = db.castDao()
        crewDao = db.crewDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun tvDao_test_upsertTvShows_And_test_deleteTvShows() = runTest {
        val tvEntities =
            testTvEntities(listOf(1, 2, 3), listOf("Tv Series 1", "Tv Series 2", "Tv Series 3"))

        // Save 3 rows into db
        tvDao.upsertTvs(tvEntities)

        // Verify 3 rows are saved
        val savedTvEntities = tvDao.getOneOffTvEntities()
        assertEquals(
            listOf(1, 2, 3), savedTvEntities.map { it.id }
        )
        assertEquals(
            listOf("Tv Series 1", "Tv Series 2", "Tv Series 3"),
            savedTvEntities.map { it.name }
        )

        // Delete 2 rows
        tvDao.deleteTvs(setOf(1, 2))
        // Verify 1 row is remaining
        val remainingTvEntities = tvDao.getOneOffTvEntities()
        assertEquals(
            listOf(3), remainingTvEntities.map { it.id }
        )
    }

    @Test
    fun tvDao_test_deleteTvShows_And_deleteCrossRefEntities_And_castDao_deleteCasts() = runTest {
        save_TvEntities_CastEntities_itsCrossRef_success()

        // Delete 1 tv show, 3 casts and its associated crossRef
        tvDao.deleteTvs(setOf(1))
        tvDao.deleteTvCastCrossRefEntities(tvIds = setOf(1), castIds = setOf(1,2,3))
        castDao.deleteCasts(setOf(1,2,3))

        // verify remaining 1 tv show, 2 casts and 2 crossRef rows
        val remainingTvEntities = tvDao.getOneOffTvEntities()
        val remainingCastEntities = castDao.getOneOffCastEntities()
        val remainingCrossRefEntities = tvDao.getTvCastCrossRefEntities(tvIds = setOf(2))
        assertEquals(listOf(2), remainingTvEntities.map { it.id })
        assertEquals(listOf(4,5), remainingCastEntities.map { it.id })
        // 2 crossRef rows remain
        assertEquals(2, remainingCrossRefEntities.size)
    }

    @Test
    fun tvDao_test_getOneOffTvShowWithCastEntities_is_correct() = runTest {
        save_TvEntities_CastEntities_itsCrossRef_success()

        // verify 1st tv show and 3 casts
        val savedTvWithCasts = tvDao.getOneOffTvWithCastEntities(1).first()
        assertEquals(1, savedTvWithCasts.tv.id)
        assertEquals(listOf(1,2,3), savedTvWithCasts.casts.map{it.id})
    }

    @Test
    fun tvDao_test_deleteTvShows_And_deleteCrossRefEntities_And_crewDao_deleteCrews() = runTest {
        save_TvEntities_CrewEntities_itsCrossRef_success()

        // Delete the 2nd tv show, 2 unique crews and its associated crossRef
        // That means remaining 1 tv show, 3 crews and 3 crossRef rows
        tvDao.deleteTvs(setOf(2))
        tvDao.deleteTvCrewCrossRefEntities(tvIds = setOf(2), crewIds = setOf(4,5))
        crewDao.deleteCrews(setOf(4,5))

        // verify remaining 1 tv show, 3 crews and 3 crossRef rows
        val remainingTvEntities = tvDao.getOneOffTvEntities()
        val remainingCrewEntities = crewDao.getOneOffCrewEntities()
        val remainingCrossRefEntities = tvDao.getTvCrewCrossRefEntities(tvIds = setOf(1))
        assertEquals(listOf(1), remainingTvEntities.map{ it.id })
        assertEquals(listOf(1,2,3), remainingCrewEntities.map{ it.id })
        // 3 crossRef rows remain
        assertEquals(3, remainingCrossRefEntities.size)
    }

    @Test
    fun tvDao_test_getOneOffTvShowWithCrewEntities_is_correct() = runTest {
       save_TvEntities_CrewEntities_itsCrossRef_success()

        // verify 2st tv show and 5 crews
        val savedTvWithCrews = tvDao.getOneOffTvWithCrewEntities(2).first()
        assertEquals(2, savedTvWithCrews.tv.id)
        assertEquals((1..5).toList(), savedTvWithCrews.crews.map{it.id})
    }

    private suspend fun save_TvEntities_CrewEntities_itsCrossRef_success() {
        val tvEntities =
            testTvEntities(listOf(1, 2), listOf("Tv Series 1", "Tv Series 2"))
        val tvCrews = testCrews(listOf(1, 2, 3, 4, 5))
        val tvCrossRefEntities = listOf(
            testTvCrewCrossRefEntities(1, (1..3).toList()),
            testTvCrewCrossRefEntities(2, (1..5).toList()),
        ).flatten()

        // Saved entities into db
        tvDao.upsertTvs(tvEntities)
        crewDao.insertOrIgnoreCrews(tvCrews)
        tvDao.insertOrIgnoreTvCrewCrossRefEntities(tvCrossRefEntities)

        // Verify 2 tv shows, 5 casts
        val savedTvEntities = tvDao.getOneOffTvEntities()
        val savedCrewEntities = crewDao.getOneOffCrewEntities()
        assertEquals(listOf(1, 2), savedTvEntities.map { it.id })
        assertEquals((1..5).toList(), savedCrewEntities.map{it.id})
    }

    private suspend fun save_TvEntities_CastEntities_itsCrossRef_success() {
        val tvEntities = testTvEntities(
            listOf(1, 2),
            listOf("Tv Series 1", "Tv Series 2")
        )
        val tvCasts = testCasts(listOf(1, 2, 3, 4, 5))
        val tvCrossRefEntities = listOf(
            testTvCastCrossRefEntities(1, (1..3).toList()),
            testTvCastCrossRefEntities(2, (1..5).toList()),
        ).flatten()

        // Saved entities into db
        tvDao.upsertTvs(tvEntities)
        castDao.insertOrIgnoreCasts(tvCasts)
        tvDao.insertOrIgnoreTvCastCrossRefEntities(tvCrossRefEntities)

        // Verify 2 tv shows, 5 casts
        val savedTvEntities = tvDao.getOneOffTvEntities()
        val savedCastEntities = castDao.getOneOffCastEntities()
        assertEquals(listOf(1, 2), savedTvEntities.map { it.id })
        assertEquals((1..5).toList(), savedCastEntities.map{it.id})
    }

    private fun testTvEntities(ids: List<Int>, titles: List<String>): List<TvEntity> =
        ids.mapIndexed { index, id ->
            TvEntity(
                adult = false,
                backdropPath = "backdropPath",
                firstAirDate = "",
                id = id,
                name = titles[index],
                overview = "overview",
                popularity = 1.0,
                posterPath = "",
                voteAverage = 8.0,
                voteCount = 1234,
            )
        }

}