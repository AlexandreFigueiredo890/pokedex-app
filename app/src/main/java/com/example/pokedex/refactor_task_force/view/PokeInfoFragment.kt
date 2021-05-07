package com.example.pokedex.refactor_task_force.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pokedex.R
import com.example.pokedex.refactor_task_force.model.PokemonEntity
import org.koin.android.ext.android.inject


class PokeInfoFragment : Fragment() {


    private val mPokemons:PokemonEntity by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poke_info2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val pokeNameTextView = view.findViewById<TextView>(R.id.poke_name_field)

        pokeNameTextView.text = mPokemons.getName()
    }




    /*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PokeInfoFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokeInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    } */
}