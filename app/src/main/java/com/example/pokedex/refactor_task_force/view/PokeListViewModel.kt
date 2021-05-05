package com.example.pokedex.refactor_task_force.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.refactor_task_force.API.PokeObjectApiListener.PokeObjectApiListener
import com.example.pokedex.refactor_task_force.model.PokeModelObject
import com.example.pokedex.refactor_task_force.repository.PokeRepositoryImp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn

class PokeListViewModel(
    val mRepository: PokeRepositoryImp
): ViewModel() {




    private var mListPokeIds = MutableLiveData<List<String>>()
    var mid: LiveData<List<String>> = mListPokeIds

    private var mPokemonList = MutableLiveData<MutableList<PokeModelObject>>()
    var mpokemonList: LiveData<MutableList<PokeModelObject>> = mPokemonList

    private var mFilteredPokemonList = MutableLiveData<MutableList<PokeModelObject>>()
    var mfilteredpokemonList: LiveData<MutableList<PokeModelObject>> = mFilteredPokemonList

    private var mErrorMessage = MutableLiveData<String>()
    var merrormessage: LiveData<String> = mErrorMessage

    private var mLoading = MutableLiveData<Boolean>()
    var mloading: LiveData<Boolean> = mLoading






    fun fetchView(){
        GlobalScope.launch{
            delay(7000L)
            mLoading.postValue(false)
        }
        mLoading.value = true

    }



    fun initialFetchPokeNames() {

        CoroutineScope(Dispatchers.IO).launch {
            mRepository.getPokemonNamesFlow(0,100).collect() { pokemon ->

                pokemon?.let {
                    mRepository.insertPokemon(it)
                }

            }
        }
    }

    fun clickEventFetchPokeNames(pokeRow:Int){
        when(pokeRow){
             1 ->{

                 CoroutineScope(Dispatchers.IO).launch {
                     mRepository.getPokemonNamesFlow(101,200).collect(){pokemon ->
                         pokemon?.let{
                             mRepository.insertPokemon(it)
                         }
                     }
                 }
             }
            2 ->{

                CoroutineScope(Dispatchers.IO).launch {
                    mRepository.getPokemonNamesFlow(201, 300).collect(){pokemon ->
                        pokemon?.let {
                            mRepository.insertPokemon(it)
                        }
                    }
                }
            }
            3 ->{

                CoroutineScope(Dispatchers.IO).launch {
                    mRepository.getPokemonNamesFlow(301,400).collect(){pokemon ->
                        pokemon?.let{
                            mRepository.insertPokemon(it)
                        }
                    }
                }
            }
            4 ->{
                CoroutineScope(Dispatchers.IO).launch{
                    mRepository.getPokemonNamesFlow(401,500).collect(){pokemon ->
                        pokemon?.let{
                            mRepository.insertPokemon(it)
                        }
                    }
                }
            }
            5 ->{
                CoroutineScope(Dispatchers.IO).launch {
                    mRepository.getPokemonNamesFlow(501,600).collect(){pokemon ->
                        pokemon?.let {
                            mRepository.insertPokemon(it)
                        }
                    }
                }
            }
            6 ->{
                CoroutineScope(Dispatchers.IO).launch {
                    mRepository.getPokemonNamesFlow(601,700).collect(){pokemon ->
                        pokemon?.let {
                            mRepository.insertPokemon(it)
                        }
                    }
                }
            }
        }
    }

    fun loadAllPokemons(){
        CoroutineScope(Dispatchers.IO + Job()).launch {
            mRepository.getAllPokemons().collect(){pokemonStream ->
                mPokemonList.postValue(pokemonStream)
            }
        }
    }

   /* fun fetchPokeNames(){

        val aux_name:MutableList<PokeModelObject> = arrayListOf()
        val job = CoroutineScope(Dispatchers.IO + Job()).launch {
            for(k in 1..700){
                mRepository.getPokemonNames(k.toString(), object: PokeObjectApiListener {
                    override fun onSucces(model: PokeModelObject) {
                        aux_name.add(model)
                        mRepository.insertPokemon(model)
                        mListPoke.postValue(aux_name)
                    }

                    override fun onFailure(str: String) {

                    }

                })
            }
        } */









    fun refresh(){


        CoroutineScope(Dispatchers.IO).launch{
            mRepository.refreshDataBase()

        }




    }

    fun searchForPokemon(query:String){

        CoroutineScope(Dispatchers.IO + Job()).launch {
            mRepository.searchForPke(query){
                 mFilteredPokemonList.postValue(it)
            }
        }



    }






}