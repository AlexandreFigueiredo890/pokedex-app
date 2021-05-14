package com.example.pokedex.refactor_task_force.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class SpecificStatModel(
        @SerializedName("name")
        val name:String? = null
)