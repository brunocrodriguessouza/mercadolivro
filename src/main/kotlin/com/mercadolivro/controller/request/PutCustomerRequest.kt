package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class PutCustomerRequest (

    @field:NotEmpty(message = "Nome deve ser informado")
    var name: String?,

    @field:Email(message = "E-mail deve ser válido")
    @field:NotBlank(message = "E-mail deverá ser preenchido")
    var email: String?
)