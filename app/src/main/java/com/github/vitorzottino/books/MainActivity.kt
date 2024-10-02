package com.github.vitorzottino.books

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.github.vitorzottino.books.adapter.MainListAdapter
import com.github.vitorzottino.books.application.BookApplication
import com.github.vitorzottino.books.databinding.ActivityMainBinding
import com.github.vitorzottino.books.databinding.DialogEditBookBinding
import com.github.vitorzottino.books.entity.Book
import com.github.vitorzottino.books.factory.MainViewModelFactory
import com.github.vitorzottino.books.modelview.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (application as BookApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpLogo()
        setUpListeners()
        setUpRecyclerView()
    }

    private fun setUpListeners() {
        binding.buttonSave.setOnClickListener {
            val bookTitle = binding.editTextTitulo.text.toString()
            val bookAutor = binding.editTextAutor.text.toString()
            val bookUrl = binding.editTextUrl.text.toString()
            if (bookTitle.isNotBlank() && bookAutor.isNotBlank()) {
                mainViewModel.insert(
                    Book(
                        titulo = bookTitle, autor = bookAutor, urlImagem = bookUrl
                    )
                )
                binding.editTextTitulo.text.clear()
                binding.editTextAutor.text.clear()
                binding.editTextUrl.text.clear()
                binding.editTextTitulo.requestFocus()
            }
        }
    }

    private fun setUpRecyclerView() {
        val adapter = MainListAdapter(onEditClick = { book -> showEditDialog(book)
        }, onDeleteClick = { book -> mainViewModel.delete(book) })
        binding.recyclerViewBooks.adapter = adapter
        binding.recyclerViewBooks.layoutManager = LinearLayoutManager(this)
        // Adicionar Divider
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewBooks.context,
            (binding.recyclerViewBooks.layoutManager as LinearLayoutManager).orientation
        )
        binding.recyclerViewBooks.addItemDecoration(dividerItemDecoration)
        mainViewModel.allBooks.observe(this) { books ->
            books?.let { adapter.setBoardGames(it) }
        }
    }

    private fun showEditDialog(book: Book) {
        val dialogBinding = DialogEditBookBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogBinding.root)
        dialogBinding.editTextTitulo.setText(book.titulo)
        dialogBinding.editTextAutor.setText(book.autor)
        builder.setTitle("Editar Livro")
        builder.setPositiveButton("Salvar") { _, _ ->
            val updatedGame = book.copy(
                titulo = dialogBinding.editTextTitulo.text.toString(),
                autor = dialogBinding.editTextAutor.text.toString(),
                urlImagem = dialogBinding.editTextUrl.text.toString()
            )
            mainViewModel.update(updatedGame)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    private fun setUpLogo() {
        Glide
            .with(this)
            .load("https://static.vecteezy.com/system/resources/previews/006/404/900/original/board-game-logo-free-vector.jpg" )
                    .into(binding.imageLogo)
    }
}