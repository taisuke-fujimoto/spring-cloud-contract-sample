plugins {
    id("org.springframework.boot")

    id("maven-publish")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$SPRING_CLOUD_DEPENDENCIES_VERSION")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // spring-boot-starter-test が含まれてる
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
