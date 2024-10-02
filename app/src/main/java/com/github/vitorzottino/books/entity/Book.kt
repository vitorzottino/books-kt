package com.github.vitorzottino.books.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo : String,
    val autor: String,
    val urlImagem: String
)
