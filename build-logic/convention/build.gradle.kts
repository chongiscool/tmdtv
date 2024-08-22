import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.wecanteen105.tmdtv.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
//    compileOnly(libs.compose.gradlePlugin)
//    compileOnly(libs.firebase.crashlytics.gradlePlugin)
//    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    implementation(libs.truth)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
//        register("androidApplicationCompose") {
//            id = "tmdtv.android.application.compose"
//            implementationClass = "AndroidApplicationComposeConventionPlugin"
//        }
        register("androidApplication") {
            id = "tmdtv.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "tmdtv.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
//        register("androidLibraryCompose") {
//            id = "tmdtv.android.library.compose"
//            implementationClass = "AndroidLibraryComposeConventionPlugin"
//        }
        register("androidLibrary") {
            id = "tmdtv.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
//        register("androidFeature") {
//            id = "tmdtv.android.feature"
//            implementationClass = "AndroidFeatureConventionPlugin"
//        }
        register("androidLibraryJacoco") {
            id = "tmdtv.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
        register("androidTest") {
            id = "tmdtv.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("hilt") {
            id = "tmdtv.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidRoom") {
            id = "tmdtv.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
//        register("androidFirebase") {
//            id = "tmdtv.android.application.firebase"
//            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
//        }
        register("androidFlavors") {
            id = "tmdtv.android.application.flavors"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
        register("androidLint") {
            id = "tmdtv.android.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("jvmLibrary") {
            id = "tmdtv.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}
