plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.intellij") version "1.16.1"
}

group = "cn.melonkid"
version = "1.0-SNAPSHOT"

repositories {
    // 阿里云仓库
    maven {
        name = "aliyun-public"
        url = uri("https://maven.aliyun.com/repository/public")
    }

    maven {
        name = "aliyun-google"
        url = uri("https://maven.aliyun.com/repository/google")
    }

    // 华为仓库
    maven {
        name = "huawei"
        url = uri("https://repo.huaweicloud.com/repository/maven/")
    }

    //mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.1.5")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("com.intellij.java"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("241.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    dependencies {
        // groovy-all is required by the Gradle IntelliJ Plugin
        implementation("org.codehaus.groovy:groovy-all:3.0.21")
        // langchain-core is required by the Gradle IntelliJ Plugin
        implementation("dev.langchain4j:langchain4j-core:0.31.0")
    }
}
