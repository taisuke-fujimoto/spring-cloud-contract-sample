import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

arrayOf(
    contract {
        description = "/person/{id} に存在する id を指定した場合、成功レスポンスになる"

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
    },
    contract {
        description = "/person/{id} に存在しない id を指定した場合、エラーレスポンス (500) になる"

        request {
            method = GET
            url = url("/person/2")
        }
        response {
            status = INTERNAL_SERVER_ERROR
        }
    }
)
