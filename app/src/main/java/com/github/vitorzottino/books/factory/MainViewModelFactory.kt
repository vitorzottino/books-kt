package com.github.vitorzottino.books.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.vitorzottino.books.modelview.MainViewModel
import com.github.vitorzottino.books.repository.BookRepository

class MainViewModelFactory (private val repository:
                            BookRepository ) : ViewModelProvider .Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom( MainViewModel ::class.java)) {
            @Suppress("UNCHECKED_CAST" )
            return MainViewModel( repository) as T
        }
        throw IllegalArgumentException( "Unknown ViewModel class" )
    }
}
