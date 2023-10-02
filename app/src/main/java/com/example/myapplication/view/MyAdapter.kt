package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Usuario
import com.example.myapplication.databinding.ItemViewBinding

class MyAdapter : ListAdapter<Usuario, MyAdapter.MyViewHolder>(DiffCallback()) {

    var clickListener: (Usuario) -> Unit = {}

    inner class MyViewHolder(private val myView: ItemViewBinding) :
        RecyclerView.ViewHolder(myView.root) {
        fun bind(item: Usuario) {
            myView.textViewUserName.text = item.nome
            myView.textViewUserEmail.text = item.email
            myView.textViewUserMatricula.text = item.matricula
            myView.textViewUserCpf.text = item.cpf
            myView.textViewUserDtNasc.text = item.dtNasc
            myView.textViewUserCurso.text = item.curso
            myView.textViewUserGenero.text = item.genero

            Glide
                .with(myView.root.context)
                .load(item.fotoUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(myView.imageViewUserFoto)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Usuario>() {
        override fun areItemsTheSame(oldItem: Usuario, newItem: Usuario): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Usuario, newItem: Usuario): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = ItemViewBinding.inflate(layoutInflater, parent, false)

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

