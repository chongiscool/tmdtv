plugins {
    alias(libs.plugins.tmdtv.jvm.library)
    alias(libs.plugins.tmdtv.hilt)
    alias(libs.plugins.kotlin.jvm)
}

//java {
//    sourceCompatibility = JavaVersion.VERSION_17
//    targetCompatibility = JavaVersion.VERSION_17
//}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}