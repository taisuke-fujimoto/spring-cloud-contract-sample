package sample.consumerWebClient.outgoing.producerWebflux.models

import sample.consumerWebClient.models.personData.PersonData

data class Person(
    val id: Long,
    val name: String,
    val surname: String
) {
    fun toPersonData(): PersonData =
        PersonData(
            key = id,
            fullName = "$name $surname".trim()
        )
}
