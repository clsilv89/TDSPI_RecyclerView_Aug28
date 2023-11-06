package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Pokemon
import com.example.myapplication.model.PokemonCompleteData
import com.example.myapplication.model.PokemonResponse
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    private val _pokemonList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _pokemonData: MutableLiveData<PokemonCompleteData> = MutableLiveData()
    val pokemonData: LiveData<PokemonCompleteData> = _pokemonData

    private fun getRequest(url: String): Request {
        builder.url(url)
        return builder.build()
    }

    fun getPokemonList() {
        setLoader(true)
        val request = getRequest(url)

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.message)
                setLoader(false)
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonResponse =
                    Gson().fromJson(response.body?.string(), PokemonResponse::class.java)
                url = jsonResponse.next.orEmpty()
                _pokemonList.postValue(jsonResponse.results)
                setLoader(false)
            }
        })
    }

    fun getPokemonData(url: String) {
        setLoader(true)
        val request = getRequest(url)

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.message)
                setLoader(false)
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonResponse =
                    Gson().fromJson(response.body?.string(), PokemonCompleteData::class.java)
                _pokemonData.postValue(jsonResponse)
                setLoader(false)
            }
        })
    }

    fun savePokemonToDatabase(pokemonCompleteData: PokemonCompleteData) {
        database
            .child("pokemon")
//            .child(pokemonCompleteData.id.toString())
            .push()
            .setValue(pokemonCompleteData)
    }

    fun deletePokemopnFromDatabase(pokemonCompleteData: PokemonCompleteData) {
        database
            .child("pokemon")
            .child(pokemonCompleteData.id.toString())
            .setValue(null)
    }

    fun getPokemonFromDatabase() {
// Adicionando direto no caminho     database.child("pokemon/5").get()
// Buscando dados inexistentes       database.child("pokemon").child("Jorge").get()
        database.child("pokemon").child("Jorge").get()
            .addOnSuccessListener {
                Log.d("Pokemon", it.toString())
            }
            .addOnFailureListener {
                Log.d("Failure", it.message.orEmpty())
            }
    }

    fun observeRealtimeDatabase() {
        database.child("pokemon")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("DataSnapshot", snapshot.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Database Failure", error.message)
                }
            })
    }

    private fun setLoader(value: Boolean) {
        _loading.postValue(value)
    }
}