package com.example.myapplication.view

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySecondBinding
import com.example.myapplication.model.PokemonCompleteData
import okhttp3.internal.wait

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

        Glide
            .with(this)
            .load(pokemon?.sprites?.frontDefault)
            .into(binding.imageViewSprite)
    }
}