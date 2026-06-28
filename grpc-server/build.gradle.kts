plugins {
    java
    idea
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.dependency.management)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.spotless)
}

group = "ru.otus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    annotationProcessor(libs.lombok.mapstruct.binding)

    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)

    implementation(libs.starter.data.jdbc)
    implementation(libs.starter.validation)
    implementation(libs.starter.flyway)
    implementation(libs.starter.grpc.server)
    implementation(libs.postgresql)
    runtimeOnly(libs.postgresql.flyway)

    testImplementation(libs.starter.grpc.server.test)
    testImplementation(libs.spring.boot.testcontainers)
    testImplementation(libs.testcontainers.postgresql)
    testImplementation(libs.protobuf.java.util)
}

tasks.test {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.30.2"
    }
}

tasks.compileJava {
    dependsOn(tasks.spotlessApply)
}

spotless {
    java {
        removeUnusedImports()
        importOrder()
        googleJavaFormat()
    }
}
