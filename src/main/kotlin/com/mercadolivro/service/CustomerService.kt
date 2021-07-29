package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.CustomerStatus
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun readAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContainingIgnoreCase(it)
        }
        return customerRepository.findAll().toList()
    }

    fun readById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = readById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }
}