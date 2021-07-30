package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class PostBookRequest (
    @field:NotEmpty(message = "Nome deve ser informado")
    var name: String,

    @field:NotBlank(message = "Preço deve ser informado")
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int
)
