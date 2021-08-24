package sample.consumerWebClient.services.personData

import org.springframework.stereotype.Service
import sample.consumerWebClient.models.personData.PersonData
import sample.consumerWebClient.outgoing.producerWebflux.clients.PersonApiClient

@Service
class PersonDataServiceImpl(
    private val personApiClient: PersonApiClient
) : PersonDataService {
    override suspend fun getPersonData(key: Long): PersonData =
        personApiClient.getPerson(key).toPersonData()
}
