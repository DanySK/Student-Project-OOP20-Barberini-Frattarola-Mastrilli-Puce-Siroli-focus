import de.aaschmid.gradle.plugins.cpd.Cpd

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
    application

    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "6.1.0"

    checkstyle
    pmd
    id("de.aaschmid.cpd") version "3.2"
    id("com.github.spotbugs") version "4.7.1"
    `build-dashboard`
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
        "base",
        "controls",
        "fxml",
        "swing",
        "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP

val javaFxVersion = "15.0.1"

val jUnitVersion = "4.12"

dependencies {
    // Example library: Guava. Add what you need (and remove Guava if you don't use it)
    // implementation("com.google.guava:guava:28.1-jre")

    // JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    // JUnit API and testing engine
    testImplementation("junit:junit:4.12")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.3.1")
    implementation("joda-time:joda-time:2.10.10")
    implementation("com.h2database:h2:1.3.148")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    // Enables JUnit 5 Jupiter module
    useJUnitPlatform()
    ignoreFailures = true
}

application {
    // Define the main class for the application
    mainClass.set("oop.focus.application.core.Launcher")

    /*
     * mainClassName was deprecated by Gradle, but it is still required by John Engelman's Shadow plugin.
     * A pull request with a fix was already merged, but it hasn't been released yet;
     * see https://github.com/johnrengelman/shadow/issues/609 and https://github.com/johnrengelman/shadow/pull/612
     */
    @Suppress("DEPRECATION")
    mainClassName = mainClass.get()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}


spotbugs {
    setEffort("max")
    setReportLevel("low")
    showProgress.set(true)
    val excludeFile = File("${project.rootProject.projectDir}/config/spotbugs/excludes.xml")
    if (excludeFile.exists()) {
        excludeFilter.set(excludeFile)
    }
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask> {
    ignoreFailures = true
    reports {
        create("html") {
            enabled = true
        }
    }
}

pmd {
    ruleSets = listOf()
    ruleSetConfig = resources.text.fromFile("${project.rootProject.projectDir}/config/pmd/pmd.xml")
    isIgnoreFailures = true
}

cpd {
    isIgnoreFailures = true
}

tasks.withType<Cpd> {
    reports {
        xml.setEnabled(false)
        text.setEnabled(true)
    }
    language = "java"
    minimumTokenCount = 50
    ignoreFailures = true
    source = sourceSets["main"].allJava
}

checkstyle {
    isIgnoreFailures = true
}
