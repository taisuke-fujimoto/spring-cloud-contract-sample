package sample.consumerWebClient.outgoing.producerWebflux.clients

import sample.consumerWebClient.outgoing.producerWebflux.models.Person

interface PersonApiClient {
    suspend fun getPerson(id: Long): Person
}
