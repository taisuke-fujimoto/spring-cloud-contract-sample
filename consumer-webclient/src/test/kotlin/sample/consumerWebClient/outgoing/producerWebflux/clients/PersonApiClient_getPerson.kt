@file:Suppress("ClassName")

package sample.consumerWebClient.outgoing.producerWebflux.clients

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import sample.consumerWebClient.outgoing.producerWebflux.exceptions.ProducerWebfluxException
import sample.consumerWebClient.outgoing.producerWebflux.models.Person

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
    // ポート番号は consumer 側 ApiClient 実装の接続先を指定する (producer 側のポートと一致する必要はない)
    ids = ["my.spring-cloud-contract-sample:producer-webflux:0.0.1-SNAPSHOT:stubs:8000"],

    // StubsMode.LOCAL: ローカル maven リポジトリを参照するモード
    // - producer 側で publishToMavenLocal を実行しておく必要がある
    //
    // StubsMode.CLASSPATH を試す場合は、 build.gradle の testImplementation(producer 側 jar 指定) コメントアウトを外す
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class PersonApiClient_getPerson(
    personApiClient: PersonApiClient
) : StringSpec({
    "存在する id は成功する" {
        val idExists = 1L
        val expected = Person(
            id = idExists,
            name = "mock_name",
            surname = "mock_surname"
        )
        val actual = personApiClient.getPerson(idExists)

        actual shouldBe expected
    }

    "存在しない id は失敗する" {
        val idNotExists = 2L

        shouldThrow<ProducerWebfluxException> {
            personApiClient.getPerson(idNotExists)
        }
    }
})
