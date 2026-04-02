build.gradle.kts (Module :app)



plugins {

&#x20;   id("com.android.application")

&#x20;   id("org.jetbrains.kotlin.android")

&#x20;   id("org.jetbrains.kotlin.plugin.compose")

&#x20;   id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"

}



android {

&#x20;   namespace = "com.example.marsphotos"

&#x20;   compileSdk = 35



&#x20;   defaultConfig {

&#x20;       applicationId = "com.example.marsphotos"

&#x20;       minSdk = 24

&#x20;       targetSdk = 35

&#x20;       versionCode = 1

&#x20;       versionName = "1.0"



&#x20;       testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

&#x20;       vectorDrawables {

&#x20;           useSupportLibrary = true

&#x20;       }

&#x20;   }



&#x20;   buildTypes {

&#x20;       release {

&#x20;           isMinifyEnabled = false

&#x20;           proguardFiles(

&#x20;               getDefaultProguardFile("proguard-android-optimize.txt"),

&#x20;               "proguard-rules.pro"

&#x20;           )

&#x20;       }

&#x20;   }

&#x20;   compileOptions {

&#x20;       sourceCompatibility = JavaVersion.VERSION\_1\_8

&#x20;       targetCompatibility = JavaVersion.VERSION\_1\_8

&#x20;   }

&#x20;   kotlinOptions {

&#x20;       jvmTarget = "1.8"

&#x20;   }

&#x20;   composeOptions {

&#x20;       kotlinCompilerExtensionVersion = "1.5.3"

&#x20;   }

&#x20;   buildFeatures {

&#x20;       compose = true

&#x20;   }

&#x20;   packaging {

&#x20;       resources {

&#x20;           excludes += "/META-INF/{AL2.0,LGPL2.1}"

&#x20;       }

&#x20;   }

}



dependencies {



&#x20;   // Import the Compose BOM

&#x20;   implementation(platform("androidx.compose:compose-bom:2024.12.01"))

&#x20;   implementation("androidx.activity:activity-compose:1.9.3")

&#x20;   implementation("androidx.compose.material3:material3")

&#x20;   implementation("androidx.compose.ui:ui")

&#x20;   implementation("androidx.compose.ui:ui-tooling-preview")

&#x20;   implementation("androidx.core:core-ktx:1.15.0")

&#x20;   implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

&#x20;   implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")



&#x20;   implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

&#x20;   implementation("com.squareup.retrofit2:retrofit:2.9.0")

&#x20;   implementation("com.squareup.okhttp3:okhttp:4.11.0")

&#x20;   implementation("io.coil-kt:coil-compose:2.4.0")

&#x20;   implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")



&#x20;   debugImplementation("androidx.compose.ui:ui-test-manifest")

&#x20;   debugImplementation("androidx.compose.ui:ui-tooling")

}





