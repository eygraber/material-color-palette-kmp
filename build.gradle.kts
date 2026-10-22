import com.eygraber.conventions.tasks.deleteRootBuildDirWhenCleaning
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

buildscript {
  dependencies {
    classpath(libs.buildscript.detekt)
    classpath(libs.buildscript.dokka)
    classpath(libs.buildscript.kotlin)
    classpath(libs.buildscript.publish)
  }
}

plugins {
  base
  alias(libs.plugins.conventions)
}

deleteRootBuildDirWhenCleaning()

gradleConventionsDefaults {
  kotlin {
    jvmTargetVersion = JvmTarget.JVM_11
  }
}

gradleConventionsKmpDefaults {
  targets(
    KmpTarget.AndroidNative,
    KmpTarget.Ios,
    KmpTarget.Js,
    KmpTarget.Jvm,
    KmpTarget.Linux,
    KmpTarget.Macos,
    KmpTarget.Mingw,
    KmpTarget.Tvos,
    KmpTarget.WasmJs,
    KmpTarget.WasmWasi,
    KmpTarget.Watchos,
  )
}
