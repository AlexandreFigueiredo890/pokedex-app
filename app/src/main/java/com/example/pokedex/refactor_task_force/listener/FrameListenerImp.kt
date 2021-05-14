package com.example.pokedex.refactor_task_force.listener

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import com.example.pokedex.refactor_task_force.model.PokemonEntity
import com.example.pokedex.refactor_task_force.view.PokeInfoActivity
import com.example.pokedex.refactor_task_force.view.PokeListActivity

class FrameListenerImp(
        val mPokemons:PokemonEntity
):FrameListener{


    override fun onClick(model:PokeModelObject, view: View) {
        val bundle = Bundle()
        val intent = Intent(view.context, PokeInfoActivity::class.java)
        bundle.putString("id", model.id.toString())
        bundle.putString("hp", model.stats[0].base_stat)
        bundle.putString("attack", model.stats[1].base_stat)
        bundle.putString("defense", model.stats[2].base_stat)
        bundle.putString("speed", model.stats[5].base_stat)

       val x = model.stats[0].modelEspecificStats?.name
       val y = model.stats[1].modelEspecificStats?.name
       val z = model.stats[2].modelEspecificStats?.name


        val pokeTest:PokeModelObject = model

        intent.putExtras(bundle)

       // val x = model.height
       // val x1 = model.weight

        model.name?.let {
            mPokemons.putName(it)
        }

        model.height?.let{
            mPokemons.putHeight(it)
        }

        model.weight?.let{
            mPokemons.putWeight(it)
        }

        view.context.startActivity(intent)



    }
}