package com.mercadolivro.service

import com.mercadolivro.model.BookModel
import com.mercadolivro.model.BookStatus
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PutMapping

@Service
class BookService (
    val bookRepository: BookRepository
)
{
    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(): List<BookModel> {
        return bookRepository.findAll().toList()
    }

    fun findActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteById(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for(book in books) {
            book.status = BookStatus.DELETADO
        }
    }
}
