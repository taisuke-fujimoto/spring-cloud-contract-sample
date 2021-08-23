package sample.consumerWebClient.controllers.personData

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sample.consumerWebClient.models.personData.PersonData
import sample.consumerWebClient.services.personData.PersonDataService

@RequestMapping("/personData")
@RestController
class IndexController(
    private val personDataService: PersonDataService
) {
    @GetMapping("/{key}")
    suspend fun getById(@PathVariable key: Long): PersonData =
        personDataService.getPersonData(key)
}
