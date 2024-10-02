package com.github.vitorzottino.books.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.vitorzottino.books.MainActivity
import com.github.vitorzottino.books.databinding.BookItemBinding
import com.github.vitorzottino.books.entity.Book

class MainListAdapter(
    private val onEditClick: (Book) -> Unit, private val onDeleteClick: (Book) -> Unit
) : RecyclerView.Adapter<MainListAdapter.BookViewHolder>() {
    private var books: List<Book> = emptyList()

    class BookViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding =
            BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = books[position]
        holder.binding.textViewTitulo.text = currentBook.titulo
        holder.binding.textViewAutor.text = currentBook.autor
        holder.binding.buttonEdit.setOnClickListener {
            onEditClick(currentBook)
        }
        holder.binding.buttonDelete.setOnClickListener {
            onDeleteClick(currentBook)
        }

        Glide.with(holder.itemView.context)
            .load(currentBook.urlImagem)
            .into(holder.binding.imageUrl)
    }

    override fun getItemCount() = books.size
    fun setBoardGames(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }
}
