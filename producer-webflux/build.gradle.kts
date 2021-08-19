import org.springframework.cloud.contract.verifier.config.TestFramework
import org.springframework.cloud.contract.verifier.config.TestMode

plugins {
    id("org.springframework.boot")

    // apply(plugin = "") 形式だと有効にならなかった
    id("org.springframework.cloud.contract")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$SPRING_CLOUD_DEPENDENCIES_VERSION")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // これに spring-boot-starter-test が含まれてる
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin")
    testImplementation("io.rest-assured:spring-web-test-client")
    // *.kts の Kotlin バージョンをこの PJ のものと合わせる (バージョンが異なる場合、 generateContractTests でエラー)
    testImplementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:$KOTLIN_VERSION")
}

contracts {
    testFramework.set(TestFramework.JUNIT5)
    testMode.set(TestMode.WEBTESTCLIENT)

    setBaseClassMappings(
        // 正規表現の指定に Kotlin の """literal""" を使うと generateContractTests でエラー
        mapOf(
            "sample[.]producerWebflux[.]controllers[.]person" to "sample.producerWebflux.controllers.person.IndexControllerBase"
        )
    )
}

tasks {
    contractTest {
        useJUnitPlatform()
    }
}
