package sample.producerWebflux.controllers.person

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import sample.producerWebflux.models.person.Person
import sample.producerWebflux.services.person.PersonService

@RequestMapping("/person")
@RestController
class IndexController(
    private val personService: PersonService
) {
    private val logger: Log = LogFactory.getLog(javaClass)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Person =
        personService.getPerson(id)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception) {
        logger.error("[handleException]", ex)
    }
}
