plugins {
    alias(libs.plugins.tmdtv.android.library)
    alias(libs.plugins.tmdtv.android.library.jacoco)
    alias(libs.plugins.tmdtv.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.wecanteen105.core.data"
}

dependencies {

    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.datastore)
    api(projects.core.network)

    implementation(projects.core.notification)

}