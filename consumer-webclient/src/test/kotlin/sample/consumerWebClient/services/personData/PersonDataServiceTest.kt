package sample.consumerWebClient.services.personData

import org.assertj.core.api.Assertions.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
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
class PersonDataServiceTest {
    // kotest でないため、こんな書き方になる
    // 素の junit5 は色々サポートされてない

    @Autowired
    private lateinit var personDataService: PersonDataService

    @Test
    fun getPersonData(): Unit = runBlocking {
        val expected = PersonData(
            key = 1,
            fullName = "mock_name mock_surname"
        )
        val actual = personDataService.getPersonData(1)

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected)
    }
}
