plugins {
    alias(libs.plugins.tmdtv.android.library)
    alias(libs.plugins.tmdtv.hilt)
}

android {
    namespace = "com.wecanteen105.core.datastore.test"
}

dependencies {

    implementation(libs.hilt.android.testing)
    implementation(projects.core.common)
    implementation(projects.core.datastore)
}