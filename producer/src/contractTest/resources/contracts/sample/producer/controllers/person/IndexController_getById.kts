import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        method = GET
        url = url("/person/1")
    }
    response {
        status = OK
        headers {
            contentType = "application/json"
        }
        body = body(
            "id" to 1,
            "name" to "mock_name",
            "surname" to "mock_surname"
        )
    }
}
