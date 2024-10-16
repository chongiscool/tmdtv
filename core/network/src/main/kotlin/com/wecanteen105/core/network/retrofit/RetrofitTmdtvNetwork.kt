package com.wecanteen105.core.network.retrofit

import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.wecanteen105.core.network.BuildConfig
import com.wecanteen105.core.network.TmdtvNetworkDataSource
import com.wecanteen105.core.network.model.NetworkMovie
import com.wecanteen105.core.network.model.NetworkTv
import com.wecanteen105.core.network.model.NetworkMovieDetail
import com.wecanteen105.core.network.model.NetworkMovieTvList
import com.wecanteen105.core.network.model.NetworkMovieTvReviews
import com.wecanteen105.core.network.model.NetworkMovieTvVideos
import com.wecanteen105.core.network.model.NetworkTvDetail
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

//curl --request GET \
//--url 'https://api.themoviedb.org/3/movie/now_playing?language=zh-CN&page=1&region=CN' \
//--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODRhZTMzMzYxZjgzNmJiMWJkYjFiMjMyOWEzYjEwOCIsIm5iZiI6MTcyNDgxMDA1OC40NDk1NDYsInN1YiI6IjU2MWNmZjYwOTI1MTQxNWE3MjAwMzkzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2vi0XQ1fD9-lQ5GrO8ZEMUBGLOOxybaun-JLUDbPxIQ' \
//--header 'accept: application/json'
//
//curl --request GET \
//--url 'https://api.themoviedb.org/3/movie/popular?language=zh-CN&page=1&region=CN' \
//--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODRhZTMzMzYxZjgzNmJiMWJkYjFiMjMyOWEzYjEwOCIsIm5iZiI6MTcyNDgxMDA1OC40NDk1NDYsInN1YiI6IjU2MWNmZjYwOTI1MTQxNWE3MjAwMzkzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2vi0XQ1fD9-lQ5GrO8ZEMUBGLOOxybaun-JLUDbPxIQ' \
//--header 'accept: application/json'
//
//curl --request GET \
//--url 'https://api.themoviedb.org/3/movie/top_rated?language=zh-CN&page=1&region=CN' \
//--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODRhZTMzMzYxZjgzNmJiMWJkYjFiMjMyOWEzYjEwOCIsIm5iZiI6MTcyNDgxMDA1OC40NDk1NDYsInN1YiI6IjU2MWNmZjYwOTI1MTQxNWE3MjAwMzkzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2vi0XQ1fD9-lQ5GrO8ZEMUBGLOOxybaun-JLUDbPxIQ' \
//--header 'accept: application/json'
//
//curl --request GET \
//--url 'https://api.themoviedb.org/3/movie/upcoming?language=zh-CN&page=1&region=CN' \
//--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODRhZTMzMzYxZjgzNmJiMWJkYjFiMjMyOWEzYjEwOCIsIm5iZiI6MTcyNDgxMDA1OC40NDk1NDYsInN1YiI6IjU2MWNmZjYwOTI1MTQxNWE3MjAwMzkzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2vi0XQ1fD9-lQ5GrO8ZEMUBGLOOxybaun-JLUDbPxIQ' \
//--header 'accept: application/json'


//curl --request GET \
//--url 'https://api.themoviedb.org/3/tv/airing_today?language=en-US&page=1&timezone=Asia%2FShanghai' \
//--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODRhZTMzMzYxZjgzNmJiMWJkYjFiMjMyOWEzYjEwOCIsIm5iZiI6MTcyNDgxMDA1OC40NDk1NDYsInN1YiI6IjU2MWNmZjYwOTI1MTQxNWE3MjAwMzkzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2vi0XQ1fD9-lQ5GrO8ZEMUBGLOOxybaun-JLUDbPxIQ' \
//--header 'accept: application/json'
//
//curl --request GET \
//--url 'https://api.themoviedb.org/3/tv/on_the_air?language=en-US&page=1&timezone=Asia%2FShanghai' \
//--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODRhZTMzMzYxZjgzNmJiMWJkYjFiMjMyOWEzYjEwOCIsIm5iZiI6MTcyNDgxMDA1OC40NDk1NDYsInN1YiI6IjU2MWNmZjYwOTI1MTQxNWE3MjAwMzkzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2vi0XQ1fD9-lQ5GrO8ZEMUBGLOOxybaun-JLUDbPxIQ' \
//--header 'accept: application/json'
//
//curl --request GET \
//--url 'https://api.themoviedb.org/3/tv/popular?language=en-US&page=1' \
//--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODRhZTMzMzYxZjgzNmJiMWJkYjFiMjMyOWEzYjEwOCIsIm5iZiI6MTcyNDgxMDA1OC40NDk1NDYsInN1YiI6IjU2MWNmZjYwOTI1MTQxNWE3MjAwMzkzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2vi0XQ1fD9-lQ5GrO8ZEMUBGLOOxybaun-JLUDbPxIQ' \
//--header 'accept: application/json'
//
//curl --request GET \
//--url 'https://api.themoviedb.org/3/tv/top_rated?language=en-US&page=1' \
//--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODRhZTMzMzYxZjgzNmJiMWJkYjFiMjMyOWEzYjEwOCIsIm5iZiI6MTcyNDgxMDA1OC40NDk1NDYsInN1YiI6IjU2MWNmZjYwOTI1MTQxNWE3MjAwMzkzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2vi0XQ1fD9-lQ5GrO8ZEMUBGLOOxybaun-JLUDbPxIQ' \
//--header 'accept: application/json'

//    curl --request GET \
//    --url 'https://api.themoviedb.org/3/movie/movie_id/videos?language=en-US' \
//    --header 'accept: application/json'
//    curl --request GET \
//    --url 'https://api.themoviedb.org/3/tv/series_id/videos?language=en-US' \
//    --header 'accept: application/json'

/**
 * Retrofit API declaration for TMDB Network API
 */
private interface RetrofitTmdtvNetworkApi {
    @GET(value = "movie/popular")
    suspend fun getPopularMovieList(@Query("page") page: Int): NetworkMovieTvList<NetworkMovie>
    @GET(value = "movie/top_rated")
    suspend fun getTopRatedMovieList(@Query("page") page: Int): NetworkMovieTvList<NetworkMovie>
    @GET(value = "movie/now_playing")
    suspend fun getNowPlayingMovieList(@Query("page") page: Int): NetworkMovieTvList<NetworkMovie>
    @GET(value = "movie/upcoming")
    suspend fun getUpcomingMovieList(@Query("page") page: Int): NetworkMovieTvList<NetworkMovie>

    @GET(value = "movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): NetworkMovieDetail

    @GET(value = "movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") movieId: Int): NetworkMovieTvReviews

    @GET(value = "movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(@Path("movie_id") movieId: Int): NetworkMovieTvList<NetworkMovie>

    @GET(value = "movie/{movie_id}/similar")
    suspend fun getMovieSimilar(@Path("movie_id") movieId: Int): NetworkMovieTvList<NetworkMovie>

    @GET(value = "movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): NetworkMovieTvVideos

    @GET(value = "tv/popular")
    suspend fun getPopularTvList(@Query("page") page: Int): NetworkMovieTvList<NetworkTv>
    @GET(value = "tv/top_rated")
    suspend fun getTopRatedTvList(@Query("page") page: Int): NetworkMovieTvList<NetworkTv>
    @GET(value = "tv/on_the_air")
    suspend fun getOnTheAirTvList(@Query("page") page: Int): NetworkMovieTvList<NetworkTv>
    @GET(value = "tv/airing_today")
    suspend fun getAiringTodayTvList(@Query("page") page: Int): NetworkMovieTvList<NetworkTv>

    @GET(value = "tv/{series_id}")
    suspend fun getTvDetail(@Path("series_id") tvId: Int): NetworkTvDetail

    @GET(value = "tv/{series_id}/reviews")
    suspend fun getTvReviews(@Path("series_id") tvId: Int): NetworkMovieTvReviews

    @GET(value = "tv/{series_id}/recommendations")
    suspend fun getTvRecommendations(@Path("series_id") tvId: Int): NetworkMovieTvList<NetworkTv>

    @GET(value = "tv/{series_id}/similar")
    suspend fun getTvSimilar(@Path("series_id") tvId: Int): NetworkMovieTvList<NetworkTv>

    @GET(value = "tv/{series_id}/videos")
    suspend fun getTvVideos(@Path("series_id") tvId: Int): NetworkMovieTvVideos
}

private const val TMDB_BASE_URL = BuildConfig.BACKEND_URL

/**
 * [Retrofit] backed [TmdtvNetworkDataSource]
 */
@Singleton
internal class  RetrofitTmdtvNetwork @Inject constructor(
    networkJson: Json,
    okHttpCallFactory: dagger.Lazy<Call.Factory>,
    ): TmdtvNetworkDataSource {

    private val networkApi = trace("RetrofitTmdtvNetwork") {
        Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okHttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitTmdtvNetworkApi::class.java)
    }

    override suspend fun getPopularMovieList(page: Int): NetworkMovieTvList<NetworkMovie> =
        networkApi.getPopularMovieList(page = page)

    override suspend fun getTopRatedMovieList(page: Int): NetworkMovieTvList<NetworkMovie> =
        networkApi.getTopRatedMovieList(page = page)

    override suspend fun getNowPlayingMovieList(page: Int): NetworkMovieTvList<NetworkMovie> =
        networkApi.getNowPlayingMovieList(page = page)

    override suspend fun getUpcomingMovieList(page: Int): NetworkMovieTvList<NetworkMovie> =
        networkApi.getUpcomingMovieList(page = page)

    override suspend fun getMovieDetail(movieId: Int): NetworkMovieDetail =
        networkApi.getMovieDetail(movieId = movieId)

    override suspend fun getMovieReviews(movieId: Int): NetworkMovieTvReviews =
        networkApi.getMovieReviews(movieId = movieId)

    override suspend fun getMovieRecommendations(movieId: Int): NetworkMovieTvList<NetworkMovie> =
        networkApi.getMovieRecommendations(movieId = movieId)

    override suspend fun getMovieSimilar(movieId: Int): NetworkMovieTvList<NetworkMovie> =
        networkApi.getMovieSimilar(movieId = movieId)

    override suspend fun getMovieVideos(movieId: Int): NetworkMovieTvVideos =
        networkApi.getMovieVideos(movieId = movieId)

    override suspend fun getPopularTvList(page: Int): NetworkMovieTvList<NetworkTv> =
        networkApi.getPopularTvList(page = page)

    override suspend fun getTopRatedTvList(page: Int): NetworkMovieTvList<NetworkTv> =
        networkApi.getTopRatedTvList(page = page)

    override suspend fun getOnTheAirTvList(page: Int): NetworkMovieTvList<NetworkTv> =
        networkApi.getOnTheAirTvList(page = page)

    override suspend fun getAiringTodayTvList(page: Int): NetworkMovieTvList<NetworkTv> =
        networkApi.getAiringTodayTvList(page = page)

    override suspend fun getTvDetail(tvId: Int): NetworkTvDetail =
        networkApi.getTvDetail(tvId = tvId)

    override suspend fun getTvReviews(tvId: Int): NetworkMovieTvReviews =
        networkApi.getTvReviews(tvId = tvId)

    override suspend fun getTvRecommendations(tvId: Int): NetworkMovieTvList<NetworkTv> =
        networkApi.getTvRecommendations(tvId = tvId)

    override suspend fun getTvSimilar(tvId: Int): NetworkMovieTvList<NetworkTv> =
        networkApi.getTvSimilar(tvId = tvId)

    override suspend fun getTvVideos(tvId: Int): NetworkMovieTvVideos =
        networkApi.getTvVideos(tvId = tvId)

}