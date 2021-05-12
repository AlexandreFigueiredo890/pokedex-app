package com.example.pokedex.refactor_task_force.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import kotlinx.coroutines.flow.Flow


@Dao
interface PokeDao {

    @Query("SELECT * FROM PokeModelObject")
    fun getAllPokemonsRoom(): Flow<MutableList<PokeModelObject>>


    @Insert
    suspend fun insertPoke(modelObject: PokeModelObject)

    @Query("DELETE  FROM PokeModelObject")
    suspend fun deleteAll()

    @Query("SELECT * FROM PokeModelObject WHERE name LIKE :query")
    suspend fun loadSearchedPokemon(query: String):MutableList<PokeModelObject>

    @Query("SELECT * FROM PokeModelObject")
    suspend fun loadPokemonsForQuery(): MutableList<PokeModelObject>
}