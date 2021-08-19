package sample.producer.services.person

import sample.producer.models.person.Person

interface PersonService {
    fun getPerson(id: Long): Person
}
