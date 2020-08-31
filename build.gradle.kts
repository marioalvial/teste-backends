plugins {
    application
    id("jacoco")
    id("com.adarshr.test-logger") version "2.0.0"
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("org.jmailen.kotlinter") version "2.1.2"
    id("io.gitlab.arturbosch.detekt") version "1.1.1"
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

repositories {
    jcenter()
}

group = "teste.backends"

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Validation
    implementation("org.glassfish:javax.el:3.0.1-b11")
    implementation("org.hibernate:hibernate-validator:6.1.2.Final")

    // Assert Framework
    testImplementation("org.assertj:assertj-core:3.15.0")

    // Test Engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.1")
    testImplementation("org.testcontainers:junit-jupiter:1.12.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.1")
}

tasks {
    assemble { dependsOn(shadowJar) }

    application {
        mainClassName = "teste.backends.SolutionKt"
    }

    test {
        useJUnitPlatform()

        System.getProperty("test.type")?.let {
            if (it == "unit") exclude("**/*integration*")
            if (it == "integration") exclude("**/*unit*")
        }
    }

    detekt {
        failFast = true
        buildUponDefaultConfig = true
        input = files("src/main/kotlin", "src/test/kotlin")
        config = files("$projectDir/detekt/config.yml")

        reports {
            xml.enabled = true

            html.enabled = false
            txt.enabled = false
        }
    }

    kotlinter {
        ignoreFailures = true
        indentSize = 4
        continuationIndentSize = 4
        reporters = arrayOf("checkstyle", "plain")
        experimentalRules = false
        disabledRules = emptyArray()
        fileBatchSize = 30
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
            csv.isEnabled = false
        }
    }

    testlogger {
        showStackTraces = false
        showCauses = false
        showSimpleNames = true
        showExceptions = true
        showPassed = true
        showStandardStreams = false
    }
}
