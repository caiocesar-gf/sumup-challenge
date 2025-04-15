import java.io.FileInputStream
import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.0" apply false
    id("com.android.library") version "8.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.0" apply false
}

rootProject.extra["config"] = Properties().apply {
    load(FileInputStream("project.properties"))
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}