package com.mercadolivro.exception

import com.mercadolivro.controller.response.ErrorResponse
import com.mercadolivro.controller.response.FieldErrorResponse
import com.mercadolivro.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {

        return ResponseEntity(
            ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.message,
                ex.errorCode,
                errors = null
            ), HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {

        return ResponseEntity(
            ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.message,
                ex.errorCode,
                errors = null
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {

        return ResponseEntity(
            ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                Errors.ML001.message,
                Errors.ML001.code,
                ex.bindingResult.fieldErrors.map{ FieldErrorResponse(it.defaultMessage ?: "invalid", it.field)}
            ), HttpStatus.UNPROCESSABLE_ENTITY
        )
    }
}