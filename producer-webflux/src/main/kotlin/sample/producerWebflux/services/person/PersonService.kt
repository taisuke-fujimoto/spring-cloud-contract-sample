package sample.producerWebflux.services.person

import sample.producerWebflux.models.person.Person

interface PersonService {
    fun getPerson(id: Long): Person
}
