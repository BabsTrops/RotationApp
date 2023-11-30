package com.example.rotations_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import com.example.rotations_app.databinding.ActivityMainBinding
import com.example.rotations_app.databinding.ActivityMenueBinding
import kotlinx.android.synthetic.main.activity_menue.*

class Menue : AppCompatActivity() {
    private lateinit var binding: ActivityMenueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //action bar
        val actionbar = supportActionBar
        actionbar!!.title= "MENÜ"

        //receive Mitarbeiter data from former activity
        var intentget= intent
        val mitarbeiterid = intent.getStringExtra("logmit");

        //Button "Nächste rotation"
        binding.nextRotation.setOnClickListener {
                val intent = Intent(this, NextRotation::class.java)
                startActivity(intent)
                //finish()
        }
        //Button "Leistungsstatistik"
        binding.meineBewertungen.setOnClickListener {
            val intent = Intent(this, Bewertungen::class.java)
            intent.putExtra("id", mitarbeiterid)
            startActivity(intent)
            //finish()
        }
       //Button "Rotation Bewerten"
        binding.ArbeitsplazBewerten.setOnClickListener {
            val intent = Intent(this, Evaluation::class.java)
            intent.putExtra("id", mitarbeiterid)
            startActivity(intent)
            //finish()
        }
        //Button "Hilfe Anfordern"
        binding.immediatrotation.setOnClickListener {
            val intent = Intent(this, Immediaterotation::class.java)
            startActivity(intent)
            //finish()
        }

            binding.adduser.setOnClickListener {
                val intent = Intent(this, AddUser::class.java)
                startActivity(intent)
                //finish()

        }
    }


}



