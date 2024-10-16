package com.wecanteen105.core.network.demo

import JvmUnitTestDemoAssetManager
import com.wecanteen105.core.common.network.Dispatcher
import com.wecanteen105.core.common.network.TmdDispatchers
import com.wecanteen105.core.network.TmdtvNetworkDataSource
import com.wecanteen105.core.network.model.NetworkMovie
import com.wecanteen105.core.network.model.NetworkMovieDetail
import com.wecanteen105.core.network.model.NetworkMovieTvList
import com.wecanteen105.core.network.model.NetworkMovieTvReviews
import com.wecanteen105.core.network.model.NetworkMovieTvVideos
import com.wecanteen105.core.network.model.NetworkTv
import com.wecanteen105.core.network.model.NetworkTvDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

/**
 * [TmdtvNetworkDataSource] implementation that provides static movie/tv data from assets to aid development
 */
class DemoTmdtvNetworkDataSource @Inject constructor(
    @Dispatcher(TmdDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoAssetManger = JvmUnitTestDemoAssetManager,
) : TmdtvNetworkDataSource {

    override suspend fun getPopularMovieList(page: Int): NetworkMovieTvList<NetworkMovie> =
        withContext(ioDispatcher) {
            assets.open(MOVIE_POPULAR_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTopRatedMovieList(page: Int): NetworkMovieTvList<NetworkMovie>  =
        withContext(ioDispatcher) {
            assets.open(MOVIE_TOP_RATED_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getNowPlayingMovieList(page: Int): NetworkMovieTvList<NetworkMovie> =
        withContext(ioDispatcher) {
            assets.open(MOVIE_NOW_PLAYING_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getUpcomingMovieList(page: Int): NetworkMovieTvList<NetworkMovie> =
        withContext(ioDispatcher) {
            assets.open(MOVIE_UPCOMING_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getMovieDetail(movieId: Int): NetworkMovieDetail =
        withContext(ioDispatcher) {
            assets.open(MOVIE_DETAIL_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getMovieReviews(movieId: Int): NetworkMovieTvReviews =
        withContext(ioDispatcher) {
            assets.open(MOVIE_REVIEWS_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getMovieRecommendations(movieId: Int): NetworkMovieTvList<NetworkMovie> =
        withContext(ioDispatcher) {
            assets.open(MOVIE_RECOMMENDATIONS_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getMovieSimilar(movieId: Int): NetworkMovieTvList<NetworkMovie> =
        withContext(ioDispatcher) {
            assets.open(MOVIE_SIMILAR_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getMovieVideos(movieId: Int): NetworkMovieTvVideos =
        withContext(ioDispatcher) {
            assets.open(MOVIE_VIDEOS_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getPopularTvList(page: Int): NetworkMovieTvList<NetworkTv> =
        withContext(ioDispatcher) {
            assets.open(TV_POPULAR_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTopRatedTvList(page: Int): NetworkMovieTvList<NetworkTv> =
        withContext(ioDispatcher) {
            assets.open(TV_TOP_RATED_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getOnTheAirTvList(page: Int): NetworkMovieTvList<NetworkTv> =
        withContext(ioDispatcher) {
            assets.open(TV_ON_THE_AIR_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getAiringTodayTvList(page: Int): NetworkMovieTvList<NetworkTv> =
        withContext(ioDispatcher) {
            assets.open(TV_AIRING_TODAY_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTvDetail(tvId: Int): NetworkTvDetail =
        withContext(ioDispatcher) {
            assets.open(TV_DETAIL_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTvReviews(tvId: Int): NetworkMovieTvReviews =
        withContext(ioDispatcher) {
            assets.open(TV_REVIEWS_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTvRecommendations(tvId: Int): NetworkMovieTvList<NetworkTv> =
        withContext(ioDispatcher) {
            assets.open(TV_RECOMMENDATIONS_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTvSimilar(tvId: Int): NetworkMovieTvList<NetworkTv> =
        withContext(ioDispatcher) {
            assets.open(TV_SIMILAR_ASSET).use(networkJson::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTvVideos(tvId: Int): NetworkMovieTvVideos =
        withContext(ioDispatcher) {
            assets.open(TV_VIDEOS_ASSET).use(networkJson::decodeFromStream)
        }

    companion object {
        private const val MOVIE_DETAIL_ASSET = "movie_detail.json"
        private const val MOVIE_POPULAR_ASSET = "movie_list.json"
        private const val MOVIE_NOW_PLAYING_ASSET = "movie_now_playing.json"
        private const val MOVIE_TOP_RATED_ASSET = MOVIE_POPULAR_ASSET
        private const val MOVIE_UPCOMING_ASSET = MOVIE_POPULAR_ASSET
        private const val MOVIE_RECOMMENDATIONS_ASSET = "movie_recommendations.json"
        private const val MOVIE_SIMILAR_ASSET = MOVIE_POPULAR_ASSET
        private const val MOVIE_REVIEWS_ASSET = "movie_reviews.json"
        private const val MOVIE_VIDEOS_ASSET = "movie_videos.json"

        private const val TV_DETAIL_ASSET = "tv_detail.json"
        private const val TV_POPULAR_ASSET = "tv_list.json"
        private const val TV_AIRING_TODAY_ASSET = "tv_list.json"
        private const val TV_TOP_RATED_ASSET = "tv_list.json"
        private const val TV_ON_THE_AIR_ASSET = "tv_list.json"
        private const val TV_RECOMMENDATIONS_ASSET = "tv_recommendations.json"
        private const val TV_SIMILAR_ASSET = "tv_similar.json"
        private const val TV_REVIEWS_ASSET = "tv_reviews.json"
        private const val TV_VIDEOS_ASSET = "tv_videos.json"


    }
}

