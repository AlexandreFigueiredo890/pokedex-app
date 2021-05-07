package com.example.pokedex.refactor_task_force.model

class PokemonEntity{


    private var id:String? = null
    private var pokeName:String? = null


    fun putName(str:String){
        pokeName = str
    }

    fun putId(Id:String){
        id = Id
    }


    fun getName(): String?{
        return pokeName
    }


    fun getId():String? {
        return id
    }

  }