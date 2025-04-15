import com.android.build.api.dsl.DefaultConfig
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")


}


val config = rootProject.extra["config"] as Properties

android {
    namespace = "com.sumup.challenge.toastcatalog"
    compileSdk = 34


    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += setOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "mozilla/public-suffix-list.txt",
                "kotlin/coroutines/coroutines.kotlin_builtins"
            )
        }
    }

    defaultConfig {
        applicationId = "com.sumup.challenge.toastcatalog"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        buildStringConfigField(fieldName = "BASE_URL", fieldValue = config.getProperty("BASE_URL"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    implementation(libs.gson)
    implementation(libs.bundles.retrofit)
    testImplementation(libs.mockwebserver)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.bundles.coil)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.koin)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}

fun DefaultConfig.buildStringConfigField(fieldName: String, fieldValue: String) {
    buildConfigField("String", fieldName, "\"$fieldValue\"")
}
