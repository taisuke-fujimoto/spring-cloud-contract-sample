package sample.producerWebflux.controllers.person

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sample.producerWebflux.models.person.Person
import sample.producerWebflux.services.person.PersonService

@RequestMapping("/person")
@RestController
class IndexController(
    private val personService: PersonService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Person =
        personService.getPerson(id)
}
