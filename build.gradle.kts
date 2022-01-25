plugins {
    java
    id("io.qameta.allure") version "2.8.1"
}

repositories {
    mavenCentral()
}
allure {
    autoconfigure = true
    version = "2.13.0"
    useJUnit5 {
        version = "2.13.0"
    }
}

dependencies {
    implementation("com.codeborne:selenide:6.2.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.1")
    testImplementation("org.assertj:assertj-core:3.21.0")
    implementation("io.qameta.allure:allure-junit5:2.13.2")
    compileOnly("org.projectlombok:lombok:1.18.22")
}

tasks.test {
 //   finalizedBy("allureReport")
    val tag: String by project
    val baseUrl: String by project

    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "fixed")
    systemProperty("junit.jupiter.execution.parallel.config.fixed.parallelism", "4")

    useJUnitPlatform {
        includeTags(tag)
    }

    if (baseUrl.isNotBlank()) {
        jvmArgs("-DbaseUrl=${baseUrl}")
    }
}