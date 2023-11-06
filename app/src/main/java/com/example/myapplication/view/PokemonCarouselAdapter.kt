package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.PokemonSpriteItemViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonCarouselAdapter :
    ListAdapter<String, PokemonCarouselAdapter.MyViewHolder>(DiffCallback()) {

    inner class MyViewHolder(private val myView: PokemonSpriteItemViewBinding) :
        RecyclerView.ViewHolder(myView.root) {

        fun bind(sprintUrl: String) {
            Glide
                .with(this.itemView.context)
                .load(sprintUrl)
                .into(myView.imageViewSprite)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout =
            PokemonSpriteItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val url = getItem(position)
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            holder.bind(url)
        }
    }
}
