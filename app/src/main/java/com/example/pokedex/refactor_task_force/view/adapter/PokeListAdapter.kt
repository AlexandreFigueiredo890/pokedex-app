package com.example.pokedex.refactor_task_force.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.refactor_task_force.listener.FrameListener
import com.example.pokedex.refactor_task_force.listener.FrameListenerImp

import com.example.pokedex.refactor_task_force.model.PokeModelObject
import com.example.pokedex.refactor_task_force.view.PokeListViewModel
import com.example.pokedex.refactor_task_force.view.viewholder.PokeListViewHolder

class PokeListAdapter(
        var mListener:FrameListenerImp
):RecyclerView.Adapter<PokeListViewHolder>() {


    private lateinit  var mList:List<PokeModelObject>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListViewHolder {
        return PokeListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_pokemons, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: PokeListViewHolder, position: Int) {
        mList[position]?.let {
            holder.bind(it, mListener)
        }
    }





    fun updateAdapter(list:List<PokeModelObject>){

        notifyDataSetChanged()
        mList = list


    }
}