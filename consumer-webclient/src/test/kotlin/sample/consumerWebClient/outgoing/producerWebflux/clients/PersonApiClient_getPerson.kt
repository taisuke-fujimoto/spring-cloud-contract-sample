@file:Suppress("ClassName")

package sample.consumerWebClient.outgoing.producerWebflux.clients

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import sample.consumerWebClient.outgoing.producerWebflux.models.Person

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
    // ポート番号は必須
    ids = ["my.spring-cloud-contract-sample:producer-webflux:0.0.1-SNAPSHOT:stubs:8000"],
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class PersonApiClient_getPerson(
    personApiClient: PersonApiClient
) : StringSpec({
    "存在する id を渡すと成功する" {
        val idExists = 1L
        val expected = Person(
            id = idExists,
            name = "mock_name",
            surname = "mock_surname"
        )
        val actual = personApiClient.getPerson(idExists)

        actual shouldBe expected
    }
})
