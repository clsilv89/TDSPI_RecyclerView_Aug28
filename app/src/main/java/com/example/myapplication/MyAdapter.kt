package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemViewBinding

class MyAdapter : ListAdapter<String, MyAdapter.MyViewHolder>(DiffCallback()) {

    var clickListener: (String) -> Unit = {}

    inner class MyViewHolder(private val myView: ItemViewBinding) :
        RecyclerView.ViewHolder(myView.root) {
            fun bind(item: String) {
                myView.itemViewTextView.text = item
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
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = ItemViewBinding.inflate(layoutInflater)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener.invoke(item)
        }
    }
}

