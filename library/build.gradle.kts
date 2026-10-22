plugins {
  id("com.eygraber.conventions-kotlin-multiplatform")
  id("com.eygraber.conventions-detekt2")
  id("com.eygraber.conventions-publish-maven-central")
}

kotlin {
  explicitApi()

  defaultKmpTargets(
    project = project,
  )

  sourceSets {
    commonTest.dependencies {
      implementation(kotlin("test"))
    }

    jvmTest.dependencies {
      implementation(libs.test.hammwerk.materialColorPalette)
    }
  }
}

configurations.named { it == "jvmTestCompileClasspath" || it == "jvmTestRuntimeClasspath" }.configureEach {
  attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 13)
}
