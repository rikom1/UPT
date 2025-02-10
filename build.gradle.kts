plugins {
    id("java")
}

group = "org.example"
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


tasks.test {
    useJUnitPlatform()
}