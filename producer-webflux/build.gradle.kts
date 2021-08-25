import org.springframework.cloud.contract.verifier.config.TestFramework
import org.springframework.cloud.contract.verifier.config.TestMode

plugins {
    id("org.springframework.boot")

    // apply(plugin = "") 形式だと有効にならなかった
    id("org.springframework.cloud.contract")

    // maven リポジトリに発行する場合、必要
    id("maven-publish")
}

group = "my.spring-cloud-contract-sample"
version = "0.0.1-SNAPSHOT"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$SPRING_CLOUD_DEPENDENCIES_VERSION")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // spring-boot-starter-test が含まれてる
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin")
    testImplementation("io.rest-assured:spring-web-test-client")
    // *.kts の Kotlin バージョンをこの PJ のものと合わせる (バージョンが異なる場合、 generateContractTests でエラー)
    testImplementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:$KOTLIN_VERSION")
}

contracts {
    testFramework.set(TestFramework.JUNIT5)
    testMode.set(TestMode.WEBTESTCLIENT)

    // contracts ファイルと、その自動生成テストコードに継承させるクラスのマッピング
    //   - key: contracts ファイルのあるディレクトリの正規表現
    //     - ディレクトリ区切りは `.` とする必要がある
    //     - contractTest/resources/contracts 配下のパスを指定する
    //   - value: 継承させるクラスの FQCN
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

// ローカルの maven リポジトリに発行するための設定
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks.named("verifierStubsJar"))

            // https://github.com/spring-gradle-plugins/dependency-management-plugin/issues/273
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
}
