package com.github.vitorzottino.books.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.vitorzottino.books.entity.Book

@Dao
interface BookDao {

    @Insert
    suspend fun insert(book: Book)

    @Query("SELECT * FROM books ORDER BY id ASC")
    fun getAllBooks(): (LiveData<List<Book>>)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)
}