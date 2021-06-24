import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.4.0"
}

group = "com.gildedrose"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
	testImplementation("io.cucumber:cucumber-junit:6.10.4")
	testImplementation("io.cucumber:cucumber-java:6.10.4")
	testImplementation("org.junit.vintage:junit-vintage-engine:5.7.2")
}

tasks.test {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "1.8"
}
