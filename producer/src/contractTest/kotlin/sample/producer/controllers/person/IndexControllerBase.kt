package sample.producer.controllers.person

import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.junit.jupiter.api.BeforeEach
import sample.producer.models.person.Person
import sample.producer.services.person.PersonService

abstract class IndexControllerBase {
    @BeforeEach
    fun setUp() {
        RestAssuredWebTestClient.standaloneSetup(IndexController(MockPersonService))
    }

    object MockPersonService : PersonService {
        override fun getPerson(id: Long): Person =
            Person(1, "mock_name", "mock_surname")
    }
}
