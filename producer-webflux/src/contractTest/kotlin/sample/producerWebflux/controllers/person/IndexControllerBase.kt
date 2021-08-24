package sample.producerWebflux.controllers.person

import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.junit.jupiter.api.BeforeEach
import sample.producerWebflux.models.person.Person
import sample.producerWebflux.services.person.PersonService

abstract class IndexControllerBase {
    @BeforeEach
    fun setUp() {
        RestAssuredWebTestClient.standaloneSetup(IndexController(MockPersonService))
    }

    object MockPersonService : PersonService {
        override fun getPerson(id: Long): Person =
            if (id == 1L) Person(1, "mock_name", "mock_surname")
            else throw RuntimeException("invalid id: $id")
    }
}
