plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.viewerc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.viewerc"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //implementation ("com.github.barteksc:pdfium-android:1.9.0")
    implementation ("com.github.mhiew:android-pdf-viewer:3.2.0-beta.1") // 2.8.2
    //implementation("com.mindorks.android:prdownloader:0.6.0")
    implementation("com.github.amitshekhariitbhu:PRDownloader:1.0.1")
    //implementation ("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1")
    //implementation("com.github.barteksc:android-pdf-viewer:2.8.2")
    //compile 'com.github.barteksc:android-pdf-viewer:2.0.3'
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}