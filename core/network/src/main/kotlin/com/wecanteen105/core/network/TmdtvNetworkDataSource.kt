package com.wecanteen105.core.network

import com.wecanteen105.core.network.model.NetworkMovieTvList
import com.wecanteen105.core.network.model.NetworkMovie
import com.wecanteen105.core.network.model.NetworkTv
import com.wecanteen105.core.network.model.NetworkMovieDetail
import com.wecanteen105.core.network.model.NetworkMovieTvReviews
import com.wecanteen105.core.network.model.NetworkMovieTvVideos
import com.wecanteen105.core.network.model.NetworkTvDetail

interface TmdtvNetworkDataSource {
    suspend fun getPopularMovieList(page: Int = 1): NetworkMovieTvList<NetworkMovie>
    suspend fun getTopRatedMovieList(page: Int = 1): NetworkMovieTvList<NetworkMovie>
    suspend fun getNowPlayingMovieList(page: Int = 1): NetworkMovieTvList<NetworkMovie>
    suspend fun getUpcomingMovieList(page: Int = 1): NetworkMovieTvList<NetworkMovie>

    suspend fun getMovieDetail(movieId: Int): NetworkMovieDetail

    suspend fun getMovieReviews(movieId: Int): NetworkMovieTvReviews

    suspend fun getMovieRecommendations(movieId: Int): NetworkMovieTvList<NetworkMovie>

    suspend fun getMovieSimilar(movieId: Int): NetworkMovieTvList<NetworkMovie>

    suspend fun getMovieVideos(movieId: Int): NetworkMovieTvVideos

    suspend fun getPopularTvList(page: Int = 1): NetworkMovieTvList<NetworkTv>
    suspend fun getTopRatedTvList(page: Int = 1): NetworkMovieTvList<NetworkTv>
    suspend fun getOnTheAirTvList(page: Int = 1): NetworkMovieTvList<NetworkTv>
    suspend fun getAiringTodayTvList(page: Int = 1): NetworkMovieTvList<NetworkTv>

    suspend fun getTvDetail(tvId: Int): NetworkTvDetail

    suspend fun getTvReviews(tvId: Int): NetworkMovieTvReviews

    suspend fun getTvRecommendations(tvId: Int): NetworkMovieTvList<NetworkTv>

    suspend fun getTvSimilar(tvId: Int): NetworkMovieTvList<NetworkTv>

    suspend fun getTvVideos(tvId: Int): NetworkMovieTvVideos
}
