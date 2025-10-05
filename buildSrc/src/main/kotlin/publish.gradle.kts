
plugins {
    id("io.github.gradle-nexus.publish-plugin")
    id("com.vanniktech.maven.publish")
}

nexusPublishing {
    // Configure maven central repository
    // https://github.com/gradle-nexus/publish-plugin#publishing-to-maven-central-via-sonatype-ossrh
    repositories {
        sonatype()

    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()
}