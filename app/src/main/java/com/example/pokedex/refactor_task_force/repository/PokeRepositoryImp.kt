package com.example.pokedex.refactor_task_force.repository


import com.example.pokedex.refactor_task_force.API.PokeObjectApiListener.PokeObjectApiListener
import com.example.pokedex.refactor_task_force.API.PokeObjectApiService
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeRepositoryImp(
    val PokeApi: PokeObjectApiService
):PokeRepository {



    private var thePokemonList: MutableList<PokeModelObject> = arrayListOf()


    override suspend fun getPokemonNamesFlow(): Flow<PokeModelObject?> = flow {
        var id:Int = 0
        
        while(id<=100){
            var pke = PokeApi.getPokeName(id.toString()).body()


            id = id + 1
            pke?.let {
                emit(it)
            }
        }

    }

    override suspend fun getAllPokemons(): Flow<MutableList<PokeModelObject>> = flow{
        val ref = FirebaseDatabase.getInstance().getReference("pokemons")

        var pokemonList:MutableList<PokeModelObject> = arrayListOf()


        ref.addValueEventListener(object:ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                pokemonList.clear()
                for( h in snapshot.children){
                    val poke = h.getValue(PokeModelObject::class.java)
                    poke?.let{
                        pokemonList.add(it)
                    }
                }
            }

        })
        emit(pokemonList)
    }


    /*override suspend fun getPokemonNames(id: String, listener: PokeObjectApiListener) {


        val call = PokeApi.getPokeName(id)

        call.enqueue(object: Callback<PokeModelObject> {
            override fun onFailure(call: Call<PokeModelObject>, t: Throwable) {
                listener.onFailure(t.toString())
            }

            override fun onResponse(
                call: Call<PokeModelObject>,
                response: Response<PokeModelObject>
            ) {
                if(response.code() != 200){
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else{
                    response.body()?.let {
                        listener.onSucces(it)
                    }
                }
            }

        })
    }*/

    override suspend fun insertPokemon(model: PokeModelObject) {
        val ref = FirebaseDatabase.getInstance().getReference("pokemons")
        val pokeId = ref.push().key


        ref.child(pokeId.toString()).setValue(PokeModelObject(pokeId, model.id, model.name))
    }



    override fun refreshDataBase() {
        val ref = FirebaseDatabase.getInstance().getReference("pokemons")
        ref.removeValue()
    }

    override fun searchForPke(
        query: String,
        onDataChangedCallback: (MutableList<PokeModelObject>) -> Unit
    ) {
        val ref = FirebaseDatabase.getInstance().getReference("pokemons")

        var aux:MutableList<PokeModelObject> = arrayListOf()
        var aux_for_empty:MutableList<PokeModelObject> = arrayListOf()


        ref.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {


                if (query.isNotEmpty()) {

                    aux.clear()
                    for (h in snapshot.children) {
                        val poke = h.getValue(PokeModelObject::class.java)
                        if (poke?.name?.contains(query) == true) {
                            aux.add(poke)
                        }
                    }
                    onDataChangedCallback(aux)
                } else {
                    aux_for_empty.clear()
                    for(h in snapshot.children){
                        val poke = h.getValue(PokeModelObject::class.java)
                        poke?.let { aux_for_empty.add(it) }
                    }
                    onDataChangedCallback(aux_for_empty)
                }
            }

        })
    }


}