package com.example.pokedex.refactor_task_force.repository


import com.example.pokedex.refactor_task_force.API.PokeObjectApiListener.PokeObjectApiListener
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import kotlinx.coroutines.flow.Flow

interface PokeRepository {



    suspend fun refreshRoom()


    suspend fun searchForPokemonInRoom(quey:String, onDataChangedCallback: (MutableList<PokeModelObject>) -> Unit)


    //suspend fun getAllPokeRoom():Flow<MutableList<PokeModelObject>>

    suspend fun insertPokeInRoom(model:PokeModelObject)

    suspend fun getPokemonNamesFlow(inferiorLimit:Int, superiorLimit:Int):Flow<PokeModelObject?>


    suspend fun getAllPokemons():Flow<MutableList<PokeModelObject>>

    //Captura pokemons através da API
   // suspend fun getPokemonNames(id:String, listener: PokeObjectApiListener)

    //Inserer pokemons no firebase
     suspend fun insertPokemon(model: PokeModelObject)




    //Reseta firebase
    fun refreshDataBase()

    //Procura por pokemon
    fun searchForPke(query:String, onDataChangedCallback:(MutableList<PokeModelObject>) -> Unit)




}