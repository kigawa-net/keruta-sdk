import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
    `maven-publish`
    signing
}

mavenPublishing {
    configure(KotlinJvm(javadocJar = JavadocJar.Dokka("dokkaHtml")))
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    if (project.hasProperty("mavenCentralUsername") ||
        System.getenv("ORG_GRADLE_PROJECT_mavenCentralUsername") != null
    )
        signAllPublications()
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