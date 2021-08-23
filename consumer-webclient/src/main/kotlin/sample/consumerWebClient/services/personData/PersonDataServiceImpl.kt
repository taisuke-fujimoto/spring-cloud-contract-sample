package sample.consumerWebClient.services.personData

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import sample.consumerWebClient.models.personData.PersonData
import sample.consumerWebClient.outgoing.models.Person

@Service
class PersonDataServiceImpl(
    builder: WebClient.Builder
) : PersonDataService {
    val webClient: WebClient = builder.baseUrl("http://localhost:8000").build()

    override suspend fun getPersonData(key: Long): PersonData {
        val spec = webClient.get().uri("/person/$key")

        return spec.awaitExchange {
            if (it.statusCode().is2xxSuccessful) it.awaitBody<Person>()
            else throw RuntimeException("invalid statusCode : ${it.statusCode()}")
        }
            .toPersonData()
    }
}
