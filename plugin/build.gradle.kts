plugins {
    id("project.common-conventions")
    alias(libs.plugins.shadow)
}

repositories {
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://mvn-repo.arim.space/lesser-gpl3/")
    mavenCentral()
}

dependencies {
    api(project(":api"))
    compileOnly(libs.spigot)
    compileOnly(libs.placeholder)
    implementation(libs.morepaperlib)

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly(libs.spigot)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks {
    shadowJar {
        relocate("space.arim", "com.xism4.sternalboard")
        archiveBaseName.set("SternalBoard")
        destinationDirectory.set(file("$rootDir/bin/"))
        minimize()
    }

    clean {
        delete("${rootDir}/bin/")
    }
}