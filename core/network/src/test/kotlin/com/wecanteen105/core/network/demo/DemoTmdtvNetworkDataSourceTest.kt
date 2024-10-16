package com.wecanteen105.core.network.demo

import JvmUnitTestDemoAssetManager
import com.wecanteen105.core.network.model.NetworkMovie
import com.wecanteen105.core.network.model.NetworkTv
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DemoTmdtvNetworkDataSourceTest {

    private lateinit var subject: DemoTmdtvNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = DemoTmdtvNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestDemoAssetManager,
        )
    }

    @Suppress("ktlint:standard:max-line-length")
    @Test
    fun `test deserialization of movie detail`() = runTest (testDispatcher) {
        assertEquals(
            "Fight Club",
            subject.getMovieDetail(550).originalTitle,
        )
        assertEquals(550,
            subject.getMovieDetail(550).id)
        assertEquals("""
            A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground "fight clubs" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.
        """.trimIndent(),
            subject.getMovieDetail(550).overview)
    }

    @Suppress("ktlint:standard:max-line-length")
    @Test
    fun `test deserialization of movie list`()  = runTest(testDispatcher) {
        assertEquals(
            NetworkMovie(
                adult = false,
                backdropPath = "/yDHYTfA3R0jFYba16jBB1ef8oIt.jpg",
                genreIds = listOf(28, 35, 878),
                id = 533535,
                originalLanguage = "en",
                originalTitle = "Deadpool & Wolverine",
                overview = "A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine.",
                popularity = 4210.061,
                posterPath = "/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
                releaseDate = "2024-07-24",
                title = "Deadpool & Wolverine",
                video = false,
                voteAverage = 7.742,
                voteCount = 2743
            ),
            subject.getPopularMovieList(page = 1).results.first()
        )
    }

    @Suppress("ktlint:standard:max-line-length")
    @Test
    fun `test deserialization of tv list`() = runTest(testDispatcher) {
        assertEquals(
            NetworkTv(
                adult = false,
                backdropPath = "/nlLKuyFz6lI0KyiY1V1AE2GwGX9.jpg",
                genreIds = listOf(35, 18, 10766),
                id = 32209,
                originCountry = listOf("PE"),
                originalLanguage = "es",
                originalName = "Al Fondo Hay Sitio",
                overview = "Al fondo hay sitio is a Peruvian TV series created in 2008-2009 by Efraín Aguilar. It deals with the problems of social differences and economic status. It's one of the most popular shows in Peru and is now being shown in Ecuador, Bolivia, Paraguay and Uruguay.",
                popularity = 5450.913,
                posterPath = "/g7cuCCkvoBuSwemTHYz3k0hvvmz.jpg",
                firstAirDate = "2009-03-30",
                name = "Al Fondo Hay Sitio",
                voteAverage = 7.86,
                voteCount = 210
                ),
            subject.getPopularTvList(page = 1).results.first()
        )
    }
}