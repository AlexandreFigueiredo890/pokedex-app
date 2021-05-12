package com.example.pokedex.refactor_task_force.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.refactor_task_force.model.PokeModelObject


@Database(entities = arrayOf(PokeModelObject::class), version = 23)
abstract  class PokeDatabase: RoomDatabase() {


    abstract fun pokeDao():PokeDao


    companion object{

        fun getDatabase(context:Context):PokeDatabase{


            val instance = Room.databaseBuilder(
                context.applicationContext,
                PokeDatabase::class.java,
                "poke_database"
            ).fallbackToDestructiveMigration().build()

            return instance
        }


    }

}