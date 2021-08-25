package sample.producerWebflux.controllers.person

import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.junit.jupiter.api.BeforeEach
import sample.producerWebflux.models.person.Person
import sample.producerWebflux.services.person.PersonService

/**
 * contracts の自動生成テストコードに継承させるクラス
 *
 * contracts ファイルとこのクラスとのマッピングは build.gradle の contracts ブロックで行う
 */
abstract class IndexControllerBase {
    @BeforeEach
    fun setUp() {
        RestAssuredWebTestClient.standaloneSetup(IndexController(MockPersonService))
    }

    // contracts の内容と一致するものを返す mock service
    // (自動生成テストコードを成功させるための内容)
    object MockPersonService : PersonService {
        override fun getPerson(id: Long): Person {
            require(id == 1L) { "invalid id: $id" }
            return Person(1, "mock_name", "mock_surname")
        }
    }
}
