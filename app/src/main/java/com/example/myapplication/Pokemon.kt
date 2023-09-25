package com.example.myapplication

data class PokemonResponse(
    val count: Int? = 0,
    val next: String? = "",
    val previous: Any? = null,
    val results: List<Pokemon>? = listOf<Pokemon>()
)

data class Pokemon(
    val id: Int? = 0,
    val name: String? = "",
    val base_experience: Int? = 0,
    val height: Int? = 0,
    val is_default: Boolean? = false,
    val order: Int? = 0,
    val weight: Int? = 0
)