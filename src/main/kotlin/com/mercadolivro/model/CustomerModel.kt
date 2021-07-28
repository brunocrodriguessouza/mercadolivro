package com.mercadolivro.model

import javax.persistence.*

@Entity
@Table(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name="name", length=100, nullable=false)
    var name: String,

    @Column(name="email", length=100, nullable=false)
    var email: String
)