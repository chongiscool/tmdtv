package com.wecanteen105.core.model

data class UserData(
    val likedMovies: Set<Int>,
    val likedTvs: Set<Int>,
    val viewedMovies: Set<Int>,
    val viewedTvs: Set<Int>,
    val viewedCasts: Set<Int>,
    val viewedCrews: Set<Int>,
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
)