package com.mercadolivro.controller.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class PostPurchaseRequest (

    @field:NotNull
    @field:Positive
    val customerId: Int,

    @field: NotNull
    val bookIds: Set<Int>
)
