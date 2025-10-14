plugins {
    id("java")
    id("application")
    id("me.champeau.jmh") version "0.7.3"
}

group = "ru.centraluniversity"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

jmh {
    fork.set(1)
    warmupIterations = 1
    iterations = 1
    resultFormat = "JSON"
    profilers = listOf("gc")
    jvmArgs = listOf("-Xlog:gc*:file=gc.log:time,uptime,level,tags")
}
