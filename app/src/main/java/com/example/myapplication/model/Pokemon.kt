package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PokemonResponse(
    val count: Int? = 0,
    val next: String? = "",
    val previous: String? = "",
    val results: List<Pokemon>? = listOf<Pokemon>()
)

data class Pokemon(
    val name: String? = "",
    val url: String? = ""
)

@Parcelize
data class PokemonCompleteData(
    val name: String,
    val order: Int,
    val id: Int,
    val height: Int,
    val width: Int,
    val types: List<PokemonType>,
    val moves: List<PokemonMoves>,
    val sprites: PokemonSprites
) : Parcelable


@Parcelize
data class PokemonSprites(
    @SerializedName(value = "back_default")
    val backDefault: String,
    @SerializedName(value = "back_female")
    val backFemale: String,
    @SerializedName(value = "back_shiny")
    val backShiny: String,
    @SerializedName(value = "back_shiny_female")
    val backShinyFemale: String,
    @SerializedName(value = "front_default")
    val frontDefault: String,
    @SerializedName(value = "front_female")
    val frontFemale: String,
    @SerializedName(value = "front_shiny")
    val frontShiny: String,
    @SerializedName(value = "front_shiny_female")
    val frontShinyFemale: String
) : Parcelable

@Parcelize
data class PokemonMoves(
    val move: PokemonMoveDetails
) : Parcelable

@Parcelize
data class PokemonMoveDetails(
    val name: String,
    val url: String
) : Parcelable

@Parcelize
data class PokemonType(
    val slot: Int,
    val type: PokemonTypeData
) : Parcelable

@Parcelize
data class PokemonTypeData(
    val name: String,
    val url: String
) : Parcelable
