plugins {
    id("java")
    idea
    id("com.github.johnrengelman.shadow") version "+"
    id("application")
}

group = "de.carinaschoppe"
version = "1.0.1"
description = "Color-Sweaper Pc-Game"


repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:+'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:+'
    implementation("org.jetbrains:annotations:+")
    implementation("org.projectlombok:lombok:+")
    annotationProcessor("org.projectlombok:lombok:+")
    testCompileOnly("org.projectlombok:lombok:+")
    testAnnotationProcessor("org.projectlombok:lombok:+")
}



tasks {

    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    test {
        useJUnitPlatform()
    }


}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

application {
    mainClass.set("me.carinasophie.Start")
}
