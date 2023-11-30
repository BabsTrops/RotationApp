package com.example.rotations_app

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.rotations_app.databinding.ActivityAbstimmenBinding
import com.example.rotations_app.databinding.ActivityBewertungenBinding

class Abstimmen : AppCompatActivity() {
    private lateinit var binding: ActivityAbstimmenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbstimmenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Actionbar
        val actionbar = supportActionBar
        actionbar!!.title= "Bitte Abstimmen:"
        actionbar.setDisplayHomeAsUpEnabled(true)

        //receive data
        var intentget= intent
        val mitarbeiterid = intent.getStringExtra("id");
        /*binding.NO.setOnClickListener{
            Showfragmentreason()
        }*/

        binding.NO.setOnClickListener {
            val intent = Intent(this, Grund::class.java)
            intent.putExtra("id", mitarbeiterid)
            startActivity(intent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /*fun Showfragmentreason(){

        val transaction = manager.beginTransaction()
        val fragment = FragmentReason()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentLoaded =true
    }*/



}
