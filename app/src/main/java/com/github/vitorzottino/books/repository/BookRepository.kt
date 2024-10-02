package com.github.vitorzottino.books.repository

import androidx.lifecycle.LiveData
import com.github.vitorzottino.books.dao.BookDao
import com.github.vitorzottino.books.entity.Book

class BookRepository (private val boardGameDao : BookDao ) {
    val allBooks : LiveData<List<Book>> =
        boardGameDao .getAllBooks()
    suspend fun insert(book: Book) {
        boardGameDao .insert(book)
    }
    suspend fun update(book: Book) {
        boardGameDao .update(book)
    }
    suspend fun delete(book: Book) {
        boardGameDao .delete(book)
    }
}