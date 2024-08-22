plugins {
    alias(libs.plugins.tmdtv.android.library)
    alias(libs.plugins.tmdtv.android.library.jacoco)
    alias(libs.plugins.tmdtv.hilt)
}

android {
    namespace = "com.wecanteen105.core.datastore"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

}

dependencies {

    api(libs.androidx.dataStore)
    api(projects.core.datastoreProto)
    api(projects.core.model)

    implementation(projects.core.common)

    testImplementation(libs.kotlinx.coroutines.test)
}