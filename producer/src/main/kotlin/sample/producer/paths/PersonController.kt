package sample.producer.paths

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/person")
@RestController
class PersonController {
    private val personMap: Map<Long, Person> =
        sequenceOf(
            Person(1, "Richard", "Gere"),
            Person(2, "Emma", "Choplin"),
            Person(3, "Anna", "Carolina")
        ).associateBy { it.id }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Person =
        personMap.getValue(id)
}
