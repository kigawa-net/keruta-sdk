
plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}
val coroutineVersion = "1.10.2"
dependencies {
// https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
// https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-reactor
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    // https://mvnrepository.com/artifact/net.kigawa.kutil/kutil
    implementation("net.kigawa.kutil:kutil:4.0.2")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
val repo = "https://github.com/kigawa-net/keruta-sdk"
publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        pom {
            name = "keruta-sdk"
            description = "keruta-sdk"
            url = repo
            properties = mapOf(
            )
            licenses {
                license {
                    name.set("MIT License")
                    url.set("http://www.opensource.org/licenses/mit-license.php")
                }
            }
            developers {
                developer {
                    id.set("net.kigawa")
                    name.set("kigawa")
                    email.set("contact@kigawa.net")
                }
            }
            scm {
                connection.set("scm:git:$repo.git")
                developerConnection.set("scm:git:$repo.git")
                url.set(repo)
            }
        }
    }
}

signing {
    if (project.hasProperty("mavenCentralUsername") ||
        System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername") != null
    ) {
        useGpgCmd()
    }
}