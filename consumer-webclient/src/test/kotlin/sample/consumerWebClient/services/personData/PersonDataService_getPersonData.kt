@file:Suppress("ClassName")

package sample.consumerWebClient.services.personData

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import sample.consumerWebClient.models.personData.PersonData

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
    // ポート番号の指定は必須っぽい
    ids = ["my.spring-cloud-contract-sample:producer-webflux:0.0.1-SNAPSHOT:stubs:8000"],
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class PersonDataService_getPersonData(
    personDataService: PersonDataService
) : StringSpec({
    "存在する key を渡すと成功する" {
        val keyExists = 1L
        val expected = PersonData(
            key = keyExists,
            fullName = "mock_name mock_surname"
        )
        val actual = personDataService.getPersonData(keyExists)

        actual shouldBe expected
    }
})
