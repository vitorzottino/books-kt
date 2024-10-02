package com.github.vitorzottino.books

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.vitorzottino.books.adapter.MainListAdapter
import com.github.vitorzottino.books.application.BookApplication
import com.github.vitorzottino.books.databinding.ActivityMainBinding
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
        //setUpLogo()
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
        val adapter = MainListAdapter(onEditClick = { book -> //showEditDialog(game)
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
}