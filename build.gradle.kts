import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.mavenPublish)
}

kotlin {
  explicitApi()

  jvm {
    compilerOptions {
      jvmTarget = JvmTarget.JVM_11
    }
  }

  js {
    browser {
      testTask {
        enabled = false
      }
    }
    nodejs()
  }

  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
    browser {
      testTask {
        enabled = false
      }
    }
    nodejs()
  }

  @OptIn(ExperimentalWasmDsl::class)
  wasmWasi {
    nodejs()
  }

  iosArm64()
  iosSimulatorArm64()
  macosArm64()
  tvosArm64()
  tvosSimulatorArm64()
  watchosArm64()
  watchosDeviceArm64()
  watchosSimulatorArm64()
  linuxX64()
  linuxArm64()
  mingwX64()
  androidNativeArm32()
  androidNativeArm64()
  androidNativeX86()
  androidNativeX64()

  sourceSets {
    commonTest.dependencies {
      implementation(kotlin("test"))
    }
    jvmTest.dependencies {
      implementation(libs.hammwerk.materialColorPalette)
    }
  }
}

configurations.named { it == "jvmTestCompileClasspath" || it == "jvmTestRuntimeClasspath" }.configureEach {
  attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 17)
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

mavenPublishing {
  publishToMavenCentral()
  signAllPublications()
}
