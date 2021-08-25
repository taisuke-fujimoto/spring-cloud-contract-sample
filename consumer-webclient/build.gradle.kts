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

    // producer 側 verifierStubsJar で作った jar を持ってきて CLASSPATH に追加する場合 (jar ファイル自体はコミット済)
    // testImplementation(files("libs/producer-webflux-0.0.1-SNAPSHOT-stubs.jar"))
}

tasks {
    test {
        useJUnitPlatform()
    }
}
