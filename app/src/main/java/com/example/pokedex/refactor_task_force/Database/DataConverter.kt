package com.example.pokedex.refactor_task_force.Database

import androidx.room.TypeConverter
import com.example.pokedex.refactor_task_force.model.StatsModel
import com.google.gson.Gson
import com.google.gson.JsonObject

class DataConverter {

    @TypeConverter
    fun listToJson(data: List<StatsModel>): String{
        return Gson().toJson(data)
    }


    @TypeConverter
    fun JsonToList(data:String): List<StatsModel>{
        return Gson().fromJson(data, Array<StatsModel>::class.java).toList()
    }

}