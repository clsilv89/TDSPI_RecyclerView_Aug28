package com.example.myapplication.view

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.PokemonSpriteItemViewBinding

class PokemonCarouselAdapter :
    ListAdapter<String, PokemonCarouselAdapter.MyViewHolder>(DiffCallback()) {

    inner class MyViewHolder(private val myView: PokemonSpriteItemViewBinding) :
        RecyclerView.ViewHolder(myView.root) {

        fun bind(sprintUrl: String) {
            Glide
                .with(myView.root.context)
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
        Handler().postDelayed(
            {
                holder.bind(url)
            }, 2000
        )
    }
}