plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.realm.kotlin") version "1.13.0"
    id("kotlin-kapt")
//    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.eduramza.personaldiaryapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eduramza.personaldiaryapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    //Compose
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    //Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    //Compose navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    //Firebase
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    //Mongo DB Realm
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"){
        version {
            strictly("1.7.3")
        }
    }
    implementation("io.realm.kotlin:library-sync:1.13.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //Google Auth
//    implementation("com.google.android.gms:play-services-auth:20.7.0")

    //Coil
    implementation("io.coil-kt:coil-compose:2.2.2")

    //Pager - Accompanist
//    implementation("com.google.accompanist:accompanist-pager:0.34.0")

    //Date-Time Picker
    implementation("io.github.vanpra.compose-material-dialogs:datetime:0.9.0")

    //Message Bar Compose
    implementation("com.github.stevdza-san:MessageBarCompose:1.0.5")

    //One-Tap Compose
    implementation("com.github.stevdza-san:OneTapCompose:1.0.0")

    //Desugar JDK - Util to not use required API Code
    implementation("com.android.tools:desugar_jdk_libs:2.0.4")


    //Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    //Debug
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}