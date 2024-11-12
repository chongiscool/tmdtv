package com.wecanteen105.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.wecanteen105.core.model.DarkThemeConfig
import com.wecanteen105.core.model.ThemeBrand
import com.wecanteen105.core.model.UserData
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

//class TmdtvPreferencesDataSource @Inject constructor(
//    private val userPreferences: DataStore<UserPreferences>,
//) {
//    val userData = userPreferences.data
//        .map {
//            UserData(
//                likedMovies = it.likedMovieIdsMap.keys,
//                likedTvs = it.likedTvIdsMap.keys,
//                viewedMovies = it.viewedMovieIdsMap.keys,
//                viewedTvs = it.viewedTvIdsMap.keys,
//                viewedCasts = it.viewedCastIdsMap.keys,
//                viewedCrews = it.viewedCrewIdsMap.keys,
//                themeBrand = when (it.themeBrand) {
//                    null,
//                    ThemeBrandProto.THEME_BRAND_UNSPECIFIED,
//                    ThemeBrandProto.UNRECOGNIZED,
//                    ThemeBrandProto.THEME_BRAND_DEFAULT,
//                    -> ThemeBrand.DEFAULT
//
//                    ThemeBrandProto.THEME_BRAND_ANDROID -> ThemeBrand.ANDROID
//                },
//                darkThemeConfig = when (it.darkThemeConfig) {
//                    null,
//                    DarkThemeConfigProto.DARK_THEME_CONFIG_UNSPECIFIED,
//                    DarkThemeConfigProto.UNRECOGNIZED,
//                    DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM,
//                    ->
//                        DarkThemeConfig.FOLLOW_SYSTEM
//
//                    DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT ->
//                        DarkThemeConfig.LIGHT
//
//                    DarkThemeConfigProto.DARK_THEME_CONFIG_DARK -> DarkThemeConfig.DARK
//                },
//                useDynamicColor = it.useDynamicColor
//            )
//        }
//
//    suspend fun setLikedMovieIds(movieIds: Set<Int>) {
//        try {
//            userPreferences.updateData {
//                it.copy {
//                    likedMovieIds.clear()
//                    likedMovieIds.putAll(movieIds.associateWith { true })
//                }
//            }
//        } catch (ioException: IOException) {
//            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
//        }
//    }
//
//    suspend fun setLikedTvIds(tvIds: Set<Int>) {
//        try {
//            userPreferences.updateData {
//                it.copy {
//                    likedTvIds.clear()
//                    likedTvIds.putAll(tvIds.associateWith { true })
//                }
//            }
//        } catch (ioException: IOException) {
//            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
//        }
//    }
//
//    suspend fun setViewedMovieIds(movieIds: Set<Int>) {
//        try {
//            userPreferences.updateData {
//                it.copy {
//                    viewedMovieIds.clear()
//                    viewedMovieIds.putAll(movieIds.associateWith { true })
//                }
//            }
//        } catch (ioException: IOException) {
//            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
//        }
//    }
//
//    suspend fun setViewedTvIds(tvIds: Set<Int>) {
//        try {
//            userPreferences.updateData {
//                it.copy {
//                    viewedTvIds.clear()
//                    viewedTvIds.putAll(tvIds.associateWith { true })
//                }
//            }
//        } catch (ioException: IOException) {
//            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
//        }
//    }
//
//    suspend fun setViewedCastIds(castIds: Set<Int>) {
//        try {
//            userPreferences.updateData {
//                it.copy {
//                    viewedCastIds.clear()
//                    viewedCastIds.putAll(castIds.associateWith { true })
//                }
//            }
//        } catch (ioException: IOException) {
//            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
//        }
//    }
//
//    suspend fun setViewedCrewIds(crewIds: Set<Int>) {
//        try {
//            userPreferences.updateData {
//                it.copy {
//                    viewedCrewIds.clear()
//                    viewedCrewIds.putAll(crewIds.associateWith { true })
//                }
//            }
//        } catch (ioException: IOException) {
//            Log.e(LOG_TAG, "Failed to update user preferences", ioException)
//        }
//    }
//
//    suspend fun setThemeBrand(themeBrand: ThemeBrand) {
//        userPreferences.updateData {
//            it.copy {
//                this.themeBrand = when (themeBrand) {
//                    ThemeBrand.DEFAULT -> ThemeBrandProto.THEME_BRAND_DEFAULT
//                    ThemeBrand.ANDROID -> ThemeBrandProto.THEME_BRAND_ANDROID
//                }
//            }
//        }
//    }
//
//    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
//        userPreferences.updateData {
//            it.copy {
//                this.darkThemeConfig = when (darkThemeConfig) {
//                    DarkThemeConfig.FOLLOW_SYSTEM ->
//                        DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM
//
//                    DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
//                    DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
//                }
//            }
//        }
//    }
//
//    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
//        userPreferences.updateData {
//            it.copy { this.useDynamicColor = useDynamicColor }
//        }
//    }
//
//    companion object {
//        private const val LOG_TAG = "TmdtvPreferences"
//    }
//}