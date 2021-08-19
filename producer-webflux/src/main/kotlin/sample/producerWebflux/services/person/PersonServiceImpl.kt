package sample.producerWebflux.services.person

import org.springframework.stereotype.Service
import sample.producerWebflux.models.person.Person

@Service
class PersonServiceImpl : PersonService {
    private val personMap: Map<Long, Person> =
        sequenceOf(
            Person(1, "Richard", "Gere"),
            Person(2, "Emma", "Choplin"),
            Person(3, "Anna", "Carolina")
        ).associateBy { it.id }

    override fun getPerson(id: Long): Person =
        personMap.getValue(id)
}
