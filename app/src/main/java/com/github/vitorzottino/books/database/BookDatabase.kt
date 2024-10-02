package com.github.vitorzottino.books.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.vitorzottino.books.dao.BookDao
import com.github.vitorzottino.books.entity.Book
import java.security.AccessControlContext

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase(){

    abstract fun bookDao() : BookDao

    companion object{
        @Volatile
        private var INSTANCE : BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }



    }
}