package com.wecanteen105.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.wecanteen105.core.model.DarkThemeConfig
import com.wecanteen105.core.model.ThemeBrand
import com.wecanteen105.core.model.UserData
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class TmdtvPreferencesDataSource @Inject constructor(
    private val userPrefsStore: DataStore<UserPreferences>,
) {
    val userDataFlow = userPrefsStore.data
        .map {
            UserData(
                likedMovies = it.likedMovieIdsMap.keys,
                likedTvs = it.likedTvIdsMap.keys,
                followedCasts = it.followedCastIdsMap.keys,
                followedCrews = it.followedCrewIdsMap.keys,
                viewedMovies = it.viewedMovieIdsMap.keys,
                viewedTvs = it.viewedTvIdsMap.keys,
                viewedCasts = it.viewedCastIdsMap.keys,
                viewedCrews = it.viewedCrewIdsMap.keys,
                themeBrand = when (it.themeBrand) {
                    null,
                    ThemeBrandProto.THEME_BRAND_UNSPECIFIED,
                    ThemeBrandProto.UNRECOGNIZED,
                    ThemeBrandProto.THEME_BRAND_DEFAULT,
                    -> ThemeBrand.DEFAULT

                    ThemeBrandProto.THEME_BRAND_ANDROID -> ThemeBrand.ANDROID
                },
                darkThemeConfig = when (it.darkThemeConfig) {
                    null,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_UNSPECIFIED,
                    DarkThemeConfigProto.UNRECOGNIZED,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM,
                    ->
                        DarkThemeConfig.FOLLOW_SYSTEM

                    DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT ->
                        DarkThemeConfig.LIGHT

                    DarkThemeConfigProto.DARK_THEME_CONFIG_DARK -> DarkThemeConfig.DARK
                },
                useDynamicColor = it.useDynamicColor,
                shouldHideOnboarding = it.shouldHideOnboarding,
            )
        }

    /**
     * Follow some casts(eg: Leonardo Dicaprio) when you open this app at the first time
     */
    suspend fun setFollowedCastIds(castIds: Set<Int>) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    followedCastIds.clear()
                    followedCastIds.putAll(castIds.associateWith { true })
                    updateShouldHideOnboardingIfNecessary()
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    /**
     * Follow or unfollow a cast
     */
    suspend fun setCastIdFollowed(castId: Int, followed: Boolean) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    if (followed) {
                        followedCastIds.put(castId, true)
                    } else {
                        followedCastIds.remove(castId)
                    }
                    updateShouldHideOnboardingIfNecessary()
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    /**
     * Follow some director(eg: Nolan) when you open this app at the first time
     */
    suspend fun setFollowedCrewIds(crewIds: Set<Int>) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    followedCrewIds.clear()
                    followedCrewIds.putAll(crewIds.associateWith { true })
                    updateShouldHideOnboardingIfNecessary()
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    /**
     * Follow and unfollow a crew(director, writer)
     */
    suspend fun setCrewIdFollowed(crewId: Int, followed: Boolean) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    if (followed) {
                        followedCrewIds.put(crewId, true)
                    } else {
                        followedCrewIds.remove(crewId)
                    }
                    updateShouldHideOnboardingIfNecessary()
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    /**
     * Thumbs up to like a movie
     */
    suspend fun setMovieLiked(movieId: Int, liked: Boolean) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    if (liked) {
                        likedMovieIds.put(movieId, true)
                    } else {
                        likedMovieIds.remove(movieId)
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    /**
     * Thumbs up to like a tv
     */
    suspend fun setTvLiked(tvId: Int, liked: Boolean) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    if (liked) {
                        likedTvIds.put(tvId, true)
                    } else {
                        likedTvIds.remove(tvId)
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    suspend fun setMovieViewed(movieId: Int, viewed: Boolean) {
        setMoviesViewed(setOf(movieId), viewed)
    }

    /**
     * incremental update to viewed movie ids
     */
    suspend fun setMoviesViewed(movieIds: Set<Int>, viewed: Boolean) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    movieIds.forEach { id ->
                        if (viewed) {
                            viewedMovieIds.put(id, true)
                        } else {
                            viewedMovieIds.remove(id)
                        }
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    suspend fun setTvViewed(tvId: Int, viewed: Boolean) {
        setTvsViewed(setOf(tvId), viewed)
    }

    /**
     * incremental update to viewed tv ids
     */
    suspend fun setTvsViewed(tvIds: Set<Int>, viewed: Boolean) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    tvIds.forEach { id ->
                        if (viewed) {
                            viewedTvIds.put(id, true)
                        } else {
                            viewedTvIds.remove(id)
                        }
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    suspend fun setCastViewed(castId: Int, viewed: Boolean) {
        setCastsViewed(setOf(castId), viewed)
    }

    /**
     * incremental update to viewed cast ids
     */
    suspend fun setCastsViewed(castIds: Set<Int>, viewed: Boolean) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    castIds.forEach { id ->
                        if (viewed) {
                            viewedCastIds.put(id, true)
                        } else {
                            viewedCastIds.remove(id)
                        }
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    suspend fun setCrewViewed(crewId: Int, viewed: Boolean) {
        setCrewsViewed(setOf(crewId), viewed)
    }

    /**
     * incremental update to viewed crew ids
     */
    suspend fun setCrewsViewed(crewIds: Set<Int>, viewed: Boolean) {
        try {
            userPrefsStore.updateData {
                it.copy {
                    crewIds.forEach { id ->
                        if (viewed) {
                            viewedCrewIds.put(id, true)
                        } else {
                            viewedCrewIds.remove(id)
                        }
                    }
                }
            }
        } catch (ioException: IOException) {
            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
        }
    }

    suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        userPrefsStore.updateData {
            it.copy {
                this.themeBrand = when (themeBrand) {
                    ThemeBrand.DEFAULT -> ThemeBrandProto.THEME_BRAND_DEFAULT
                    ThemeBrand.ANDROID -> ThemeBrandProto.THEME_BRAND_ANDROID
                }
            }
        }
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        userPrefsStore.updateData {
            it.copy {
                this.darkThemeConfig = when (darkThemeConfig) {
                    DarkThemeConfig.FOLLOW_SYSTEM ->
                        DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM

                    DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                    DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                }
            }
        }
    }

    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        userPrefsStore.updateData {
            it.copy { this.useDynamicColor = useDynamicColor }
        }
    }

    /**
     * shouldHideOnboarding means: if you are NOT following some cast
     * or crew(director, writer), show onboarding to you.
     */
    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) {
        userPrefsStore.updateData {
            it.copy { this.shouldHideOnboarding = shouldHideOnboarding }
        }
    }

    companion object {
        private const val LOG_TAG = "TmdtvPreferences"
    }
}

private fun UserPreferencesKt.Dsl.updateShouldHideOnboardingIfNecessary() {
    if (followedCastIds.isEmpty() && followedCrewIds.isEmpty()) {
        shouldHideOnboarding = false
    }
}