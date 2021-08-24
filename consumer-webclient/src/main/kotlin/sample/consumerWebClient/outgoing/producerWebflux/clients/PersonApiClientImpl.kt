package sample.consumerWebClient.outgoing.producerWebflux.clients

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import sample.consumerWebClient.outgoing.producerWebflux.config.ProducerWebfluxConfig
import sample.consumerWebClient.outgoing.producerWebflux.exceptions.ProducerWebfluxException
import sample.consumerWebClient.outgoing.producerWebflux.models.Person

@Component
class PersonApiClientImpl(
    private val producerWebfluxConfig: ProducerWebfluxConfig
) : PersonApiClient {
    private val webClient: WebClient get() = producerWebfluxConfig.webClient

    override suspend fun getPerson(id: Long): Person {
        val spec = webClient.get().uri("/person/$id")
        return spec.awaitExchange {
            if (it.statusCode().is2xxSuccessful) it.awaitBody()
            else throw ProducerWebfluxException("invalid statusCode : ${it.statusCode()}")
        }
    }
}
