package com.example.pokedex.refactor_task_force.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
 data class PokeModelObject(
    @PrimaryKey(autoGenerate = true)
    val pid:Int =0,
    @SerializedName("id")
    @ColumnInfo(name="id")
    val id:String? = null,
    @SerializedName("name")
    @ColumnInfo(name="name")
    val name:String? = null,
    @SerializedName("height")
    @ColumnInfo(name = "height")
    val height:String? = null,
    @SerializedName("weight")
    @ColumnInfo(name = "weight")
    val weight:String? = null,
    @SerializedName("stats")
    @ColumnInfo(name="stats")
    val stats:List<StatsModel>

)




