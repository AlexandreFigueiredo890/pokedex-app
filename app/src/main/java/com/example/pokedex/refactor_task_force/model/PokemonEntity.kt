package com.example.pokedex.refactor_task_force.model

class PokemonEntity{


    private var id:String? = null
    private var pokeName:String? = null
    private var pokeHeight:String? = null
    private var pokeWeight:String? = null


    fun putName(str:String){
        pokeName = str
    }

    fun putId(Id:String){
        id = Id
    }

    fun putHeight(height:String){

        pokeHeight = height
    }


    fun putWeight(weight:String){
        pokeWeight = weight
    }


    fun getName(): String?{
        return pokeName
    }


    fun getId():String? {
        return id
    }

    fun getHeight():String?{
        return pokeHeight
    }


    fun getWeight(): String? {
        return pokeWeight
    }

  }