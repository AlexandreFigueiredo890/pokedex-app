package com.example.pokedex.refactor_task_force.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.add
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.example.pokedex.R
import com.example.pokedex.refactor_task_force.model.PokemonEntity
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.concurrent.fixedRateTimer

class PokeInfoActivity : AppCompatActivity(), View.OnClickListener {

    private var curtain: View? = null
    private var back_pokeball:ImageView? = null
    private var poke_image:ImageView? = null
    private var back_icon:ImageView? = null
    private var swablu:LottieAnimationView? = null
    private var name_field: TextView? = null
    private var bottomSheet:View? = null
    private var hp:TextView? = null
    private var attack:TextView? = null
    private var defense:TextView? = null
    private var speed:TextView? = null

    private val mInfoViewModel: InfoViewModel by viewModel()
    private val mInfoFragment: PokeInfoFragment by inject()
    private val mPokemonEntity:PokemonEntity by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_info)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        fetchIds()
        getData()

        swablu?.setAnimation("swablu.json")
        swablu?.playAnimation()

        mInfoViewModel.fetchView()
        onObserver()
        onListener()

        supportFragmentManager.beginTransaction()
                .add(R.id.the_bottom_sheet,mInfoFragment)
                .commit()
    }

    private fun getData(){
        val extras = intent.extras
        val id = extras?.getString("id")
        val name = extras?.getString("name")
        val hpValue = extras?.getString("hp")
        val attackValue = extras?.getString("attack")
        val defenseValue = extras?.getString("defense")
        val speedValue = extras?.getString("speed")

        hp?.text = "HP: ${hpValue}"
        attack?.text = "ATTACK: ${attackValue}"
        defense?.text = "DEFENSE: ${defenseValue}"
        speed?.text = "SPEED: ${speedValue}"
        name_field?.setText(name)
        Picasso.with(this).load("https://pokeres.bastionbot.org/images/pokemon/${id}.png").into(poke_image)
    }

    private fun onListener(){
        back_icon?.setOnClickListener(this)
    }

    private fun fetchIds(){
        curtain = findViewById(R.id.loading_curtain)
        back_pokeball = findViewById(R.id.pokeball_back)
        poke_image = findViewById(R.id.poke_image)
        back_icon = findViewById(R.id.back_icon)
        swablu = findViewById(R.id.swablu)
        name_field = findViewById(R.id.pke_name)
        bottomSheet = findViewById(R.id.the_bottom_sheet)
        hp = findViewById(R.id.hp_pokemon)
        attack = findViewById(R.id.attack_pokemon)
        defense = findViewById(R.id.defense_pokemon)
        speed = findViewById(R.id.speed_pokemon)
    }


    private fun onObserver(){
        mInfoViewModel.loading.observe(this, Observer{
            if(it){
                curtain?.visibility = View.VISIBLE
                swablu?.visibility = View.VISIBLE
                back_pokeball?.visibility = View.GONE
                poke_image?.visibility = View.GONE
                back_icon?.visibility = View.GONE
                name_field?.visibility= View.GONE
                bottomSheet?.visibility = View.GONE
                hp?.visibility = View.GONE
                attack?.visibility = View.GONE
                defense?.visibility = View.GONE
                speed?.visibility = View.GONE


            } else {

                curtain?.visibility = View.GONE
                swablu?.visibility = View.GONE
                back_pokeball?.visibility = View.VISIBLE
                poke_image?.visibility = View.VISIBLE
                back_icon?.visibility = View.VISIBLE
                name_field?.visibility = View.VISIBLE
                bottomSheet?.visibility = View.VISIBLE
                hp?.visibility = View.VISIBLE
                attack?.visibility = View.VISIBLE
                defense?.visibility = View.VISIBLE
                speed?.visibility = View.VISIBLE

            }
        })
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if(id == R.id.back_icon){
            finish()
        }
    }
}