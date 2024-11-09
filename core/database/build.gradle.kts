plugins {
    alias(libs.plugins.tmdtv.android.library)
    alias(libs.plugins.tmdtv.android.library.jacoco)
    alias(libs.plugins.tmdtv.android.room)
    alias(libs.plugins.tmdtv.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.wecanteen105.core.database"
}

dependencies {

    api(projects.core.model)

    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
}