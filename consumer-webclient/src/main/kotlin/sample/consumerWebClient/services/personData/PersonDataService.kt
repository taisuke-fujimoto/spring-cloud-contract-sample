package sample.consumerWebClient.services.personData

import sample.consumerWebClient.models.personData.PersonData

interface PersonDataService {
    suspend fun getPersonData(key: Long): PersonData
}
