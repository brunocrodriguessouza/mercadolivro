package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.model.BookModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController (
    val bookService: BookService,
    val customerService: CustomerService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.readById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun readAll(): List<BookModel> {
        return bookService.findAll()
    }

    @GetMapping("/active")
    fun readActive(): List<BookModel> {
       return bookService.findActives()
    }

    @GetMapping("/{id}")
    fun readById(@PathVariable id: Int) : BookModel {
        return bookService.findById(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        bookService.deleteById(id)
    }
}