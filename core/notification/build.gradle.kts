plugins {
    alias(libs.plugins.tmdtv.android.library)
    alias(libs.plugins.tmdtv.hilt)
}

android {
    namespace = "com.wecanteen105.core.notification"
}

dependencies {
    api(projects.core.model)

    implementation(projects.core.common)

}