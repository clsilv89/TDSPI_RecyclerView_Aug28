package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var list = arrayListOf("Analise e Desenvolvimento de Sistemas",
        "Engenharia da Computação", "Direito", "Gastronomia", "Arquitetura e Urbanismo",
        "Design Digital", "Design de Jogos", "Analise e Desenvolvimento de Sistemas",
        "Engenharia da Computação", "Direito", "Gastronomia", "Arquitetura e Urbanismo",
        "Design Digital", "Design de Jogos", "Analise e Desenvolvimento de Sistemas",
        "Engenharia da Computação", "Direito", "Gastronomia", "Arquitetura e Urbanismo",
        "Design Digital", "Design de Jogos", "Analise e Desenvolvimento de Sistemas",
        "Engenharia da Computação", "Direito", "Gastronomia", "Arquitetura e Urbanismo",
        "Design Digital", "Design de Jogos", "Analise e Desenvolvimento de Sistemas",
        "Engenharia da Computação", "Direito", "Gastronomia", "Arquitetura e Urbanismo",
        "Design Digital", "Design de Jogos", "Analise e Desenvolvimento de Sistemas",
        "Engenharia da Computação", "Direito", "Gastronomia", "Arquitetura e Urbanismo",
        "Design Digital", "Design de Jogos")
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListView(list)
    }

    private fun setupListView(cursos: ArrayList<String>) {
        val adapter = MyAdapter()
        adapter.clickListener = {
            cursos.add(it)
            adapter.notifyDataSetChanged()
        }
        adapter.submitList(cursos)
        binding.recyclerView.adapter = adapter
    }
}