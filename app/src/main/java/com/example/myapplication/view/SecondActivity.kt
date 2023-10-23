package com.example.myapplication.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySecondBinding
import com.example.myapplication.model.PokemonCompleteData

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val pokemon =
            intent
                ?.extras
                ?.getParcelable<PokemonCompleteData>(
                    "POKEMON_COMPLETE_DATA"
                )

        binding.textViewName.text = pokemon?.name
        binding.textViewNumber.text = getString(R.string.pokemon_order, pokemon?.id.toString())
        binding.textViewHeight.text =
            getString(R.string.pokemon_height, pokemon?.height.toString())
        binding.textViewWidth.text = getString(R.string.pokemon_weight, pokemon?.weight.toString())
        binding.root.setBackgroundColor(getBackgroundColor(pokemon?.types?.get(0)?.type?.name.orEmpty()))

//        if (pokemon != null) {
//            setupCarouselRecyclerView(getSpritesList(pokemon))
//        }

        pokemon?.let {
            setupCarouselRecyclerView(getSpritesList(it))
        }

//        Glide
//            .with(this)
//            .load(pokemon?.sprites?.frontDefault)
//            .into(binding.imageViewSprite)
    }

    private fun setupCarouselRecyclerView(list: List<String>) {
        val adapter = PokemonCarouselAdapter()
        adapter.submitList(list)
        val carousel = binding.carouselImageSprites
        val layoutManager = carousel.getCarouselLayoutManager()

        carousel.layoutManager = layoutManager
        carousel.adapter = adapter
    }

    private fun getBackgroundColor(pokemonType: String): Int {
        return when (pokemonType) {
            "normal" -> resources.getColor(R.color.normal, null)
            "fighting" -> resources.getColor(R.color.fighting, null)
            "flying" -> resources.getColor(R.color.flying, null)
            "poison" -> resources.getColor(R.color.poison, null)
            "ground" -> resources.getColor(R.color.ground, null)
            "rock" -> resources.getColor(R.color.rock, null)
            "bug" -> resources.getColor(R.color.bug, null)
            "ghost" -> resources.getColor(R.color.ghost, null)
            "steel" -> resources.getColor(R.color.steel, null)
            "fire" -> resources.getColor(R.color.fire, null)
            "water" -> resources.getColor(R.color.water, null)
            "grass" -> resources.getColor(R.color.grass, null)
            "electric" -> resources.getColor(R.color.electric, null)
            "psychic" -> resources.getColor(R.color.psychic, null)
            "ice" -> resources.getColor(R.color.ice, null)
            "dragon" -> resources.getColor(R.color.dragon, null)
            "dark" -> resources.getColor(R.color.dark, null)
            "fairy" -> resources.getColor(R.color.fairy, null)
            "shadow" -> resources.getColor(R.color.shadow, null)
            "unknown" -> resources.getColor(R.color.unknown, null)
            else -> 1
        }
    }

    private fun getSpritesList(pokemonCompleteData: PokemonCompleteData): List<String> {
        pokemonCompleteData.sprites.apply {
            return arrayListOf(
                this.backDefault.orEmpty(),
                this.frontDefault.orEmpty(),
                this.backFemale.orEmpty(),
                this.frontFemale.orEmpty(),
                this.backShiny.orEmpty(),
                this.frontShiny.orEmpty(),
                this.backShinyFemale.orEmpty(),
                this.frontShinyFemale.orEmpty()
            ).filter { it.isNotEmpty() }
        }
    }
}