plugins {
    id("root")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project("client"))
    implementation(project("client:model"))
    implementation(project("client:infrastructure"))
    implementation(project("common"))
    implementation(project("common:model"))
    implementation(project("common:infrastructure"))
    implementation(project("server"))
    implementation(project("server:model"))
    implementation(project("server:infrastructure"))
}

