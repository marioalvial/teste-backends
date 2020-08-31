plugins {
    application
    id("com.adarshr.test-logger") version "2.0.0"
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

repositories {
    jcenter()
}

group = "teste.backends"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
//    // Align versions of all Kotlin components
//
//    // Use the Kotlin JDK 8 standard library.
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

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
//    jar {
//        manifest {
//            attributes["Main-Class"] = "teste.backends.SolutionKt"
//        }
//
//    }

    test {
        useJUnitPlatform()

        System.getProperty("test.type")?.let {
            if (it == "unit") exclude("**/*integration*")
            if (it == "integration") exclude("**/*unit*")
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
