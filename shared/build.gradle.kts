plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose.jetbrains)

            //put your multiplatform dependencies here
        }

        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
        }

        iosMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}


android {
    namespace = "com.example.washapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    dependencies {

        val composeBom = platform("androidx.compose:compose-bom:2024.03.00")
        implementation(composeBom)
        androidTestImplementation(composeBom)

        // Android Lifecycle ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

        // Material Design 3
        implementation("androidx.compose.material3:material3")

        // Android Studio Preview support
        implementation("androidx.compose.ui:ui-tooling-preview")
        debugImplementation("androidx.compose.ui:ui-tooling")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

        // UI Tests
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        // Optional - Included automatically by material, only add when you need
        // the icons but not the material library (e.g. when using Material3 or a
        // custom design system based on Foundation)
        implementation("androidx.compose.material:material-icons-core")
        // Optional - Add full set of material icons
        implementation("androidx.compose.material:material-icons-extended")
        // Optional - Add window size utils
        implementation("androidx.compose.material3:material3-window-size-class")

        // Optional - Integration with activities
        implementation("androidx.activity:activity-compose:1.8.2")
        // Optional - Integration with ViewModels
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
        // Optional - Integration with LiveData
        implementation("androidx.compose.runtime:runtime-livedata")
        // Optional - Integration with RxJava
        implementation("androidx.compose.runtime:runtime-rxjava2")

    }

}
dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}
