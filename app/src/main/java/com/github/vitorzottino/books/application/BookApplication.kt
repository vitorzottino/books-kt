package com.github.vitorzottino.books.application

import android.app.Application
import com.github.vitorzottino.books.database.BookDatabase
import com.github.vitorzottino.books.repository.BookRepository

class BookApplication : Application() {
    val database by lazy { BookDatabase.getDatabase( this) }
    val repository by lazy {
        BookRepository( database.bookDao()) }
}