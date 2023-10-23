package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Pokemon
import com.example.myapplication.databinding.PokemonItemViewBinding

class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.MyViewHolder>(DiffCallback()) {

    var clickListener: (Pokemon) -> Unit = {}

    inner class MyViewHolder(private val myView: PokemonItemViewBinding) :
        RecyclerView.ViewHolder(myView.root) {
        fun bind(pokemon: Pokemon) {
            myView.textViewPokemonName.text = pokemon.name
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = PokemonItemViewBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
        holder.itemView.setOnClickListener {
            clickListener(pokemon)
        }
    }
}