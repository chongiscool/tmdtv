import com.wecanteen105.tmdtv.TmdtvBuildType

plugins {
    alias(libs.plugins.tmdtv.android.application)
    alias(libs.plugins.tmdtv.android.application.jacoco)
    alias(libs.plugins.tmdtv.android.application.flavors)
    alias(libs.plugins.tmdtv.hilt)
}

android {
    namespace = "com.wecanteen105.tmdtv"

    defaultConfig {
        applicationId = "com.wecanteen105.tmdtv"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = TmdtvBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = false
            applicationIdSuffix = TmdtvBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {

    // Features

    // Core
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.window.core)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)

    ksp(libs.hilt.compiler)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

dependencyGuard {
    configuration("prodReleaseRuntimeClasspath")
}
