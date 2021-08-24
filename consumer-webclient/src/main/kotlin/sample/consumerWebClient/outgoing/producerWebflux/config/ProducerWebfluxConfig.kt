package sample.consumerWebClient.outgoing.producerWebflux.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ProducerWebfluxConfig(
    private val builder: WebClient.Builder
) {
    val webClient: WebClient by lazy {
        builder.baseUrl("http://localhost:8000").build()
    }
}
