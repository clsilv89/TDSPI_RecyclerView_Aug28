package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Pokemon
import com.example.myapplication.model.PokemonCompleteData
import com.example.myapplication.viewmodel.PokemonViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private val viewModel = PokemonViewModel()
    private val adapter = PokemonAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        viewModel.getPokemonList()
    }

    private fun setupObservers() {
        viewModel.pokemonList.observe(this) {
            val list = arrayListOf<Pokemon>()
            list.addAll(adapter.currentList)
            list.addAll(it)
            adapter.submitList(list)
        }

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.pokemonData.observe(this) {
            val intent = Intent(this, SecondActivity::class.java)
            val string = Gson().toJson(it)
            intent.putExtra("POKEMON_COMPLETE_DATA", it)

            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.clickListener = {
            viewModel.getPokemonData(it.url.orEmpty())
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getPokemonList()
                }
            }
        })
    }
}
