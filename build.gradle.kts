plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.riko"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.formdev:flatlaf:3.1")
    implementation("li.flor:native-j-file-chooser:1.6.4")
    implementation("org.openjfx:javafx:17")
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "org.riko.ui.Main"
    }
}


tasks.test {
    useJUnitPlatform()
}