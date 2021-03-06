package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("book")
class BookController (
    val bookService: BookService,
    val customerService: CustomerService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest) {
        val customer = customerService.readById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun readAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findAll(pageable).map{ it.toResponse() }
    }

    @GetMapping("/active")
    fun readActive(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
       return bookService.findActives(pageable).map{ it.toResponse()}
    }

    @GetMapping("/{id}")
    fun readById(@PathVariable id: Int) : BookResponse {
        return bookService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody @Valid book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        bookService.deleteById(id)
    }
}