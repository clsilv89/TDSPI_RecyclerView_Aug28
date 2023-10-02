package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Pokemon
import com.example.myapplication.model.PokemonResponse
import com.google.gson.Gson
import java.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class PokemonViewModel : ViewModel() {
    private var url = "https://pokeapi.co/api/v2/pokemon?limit=100&offset=0"
    private val client = OkHttpClient()
    private val builder = Request.Builder()

    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private fun getRequest(url: String): Request {
        builder.url(url)
        return builder.build()
    }

    fun getPokemonList() {
        val request = getRequest(url)

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonResponse =
                    Gson().fromJson(response.body?.string(), PokemonResponse::class.java)
                url = jsonResponse.next.orEmpty()
                _pokemonList.postValue(jsonResponse.results)
            }
        })
    }
}