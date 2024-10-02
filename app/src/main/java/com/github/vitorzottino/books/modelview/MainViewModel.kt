package com.github.vitorzottino.books.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.vitorzottino.books.entity.Book
import com.github.vitorzottino.books.repository.BookRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: BookRepository) : ViewModel() {
    val allBooks: LiveData<List<Book>> = repository.allBooks
    fun insert(book: Book) {
        viewModelScope.launch {
            repository.insert(book)
        }
    }

    fun update(book: Book) {
        viewModelScope.launch {
            repository.update(book)
        }
    }

    fun delete(book: Book) {
        viewModelScope.launch {
            repository.delete(book)
        }
    }
}
