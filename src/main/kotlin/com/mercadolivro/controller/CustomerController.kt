package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController(
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @GetMapping
    fun readAll(@RequestParam name: String?): List<CustomerModel> {
        return customerService.readAll(name)
    }

    @GetMapping("/{id}")
    fun readById(@PathVariable id: Int): CustomerModel {
        return customerService.readById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        val customerSaved = customerService.readById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}