plugins {
    alias(libs.plugins.tmdtv.android.library)
    alias(libs.plugins.tmdtv.android.library.jacoco)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.wecanteen105.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)


}