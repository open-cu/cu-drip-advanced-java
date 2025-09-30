plugins {
    id("java")
    id("me.champeau.jmh") version "0.7.3"
}

group = "ru.centraluniversity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.openjdk.jmh:jmh-core:1.37")
    annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.test {
    useJUnitPlatform()
}

jmh {
    resultFormat.set("JSON")
    fork.set(1)
    warmupIterations.set(1)
    iterations.set(2)
    timeUnit.set("ms")
}

tasks.test {
    useJUnitPlatform()
}