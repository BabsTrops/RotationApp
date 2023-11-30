package com.example.rotations_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rotations_app.DBHelper.DBHelper
import com.example.rotations_app.Mitarbeiter.Mitarbeiter
import com.example.rotations_app.databinding.ActivityGrundBinding

class Grund : Base() {
    private lateinit var binding: ActivityGrundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGrundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Actionbar
        val actionbar = supportActionBar
        actionbar!!.title= "Bitte Abstimmen:"
        actionbar.setDisplayHomeAsUpEnabled(true)

        var intentget= intent
        val mitarbeiterid = intent.getStringExtra("id");

        val handler = DBHelper(this)
        var emp: Mitarbeiter = handler.viewsingleMitarbeiter(mitarbeiterid.toString())

        binding.KUeber.setOnClickListener {

            var change = emp.levelK-1
            handler.updateMitarbeiter(Mitarbeiter(emp.id, emp.name.toString(), emp.password.toString(), change, emp.levelG))
            println("Mitarbeiter ID: ${mitarbeiterid}")
            println("Mitarbeiter Name: ${emp.name}")
            println("Mitarbeiter PW: ${emp.password}")
            println("Mitarbeiter levelk: ${emp.levelK}")
            println("Mitarbeiter levelg: ${emp.levelG}")
            returntomenue()
        }

        binding.KUnter.setOnClickListener {

           //dbHandler.deleteEmployee(Mitarbeiter(8,"hanswurst","p",5,5))
            returntomenue()
        }

        binding.GUeber.setOnClickListener {
            //val change:Int = loggedmitarbeiter.levelG-1
            //val dgh = DBHelper(this)
            //dgh.updateMitarbeiter(Mitarbeiter(loggedmitarbeiter.id,"aperol",loggedmitarbeiter.password.toString(), loggedmitarbeiter.levelK, change))
            returntomenue()
        }

        binding.GUnter.setOnClickListener {
            //loggedmitarbeiter.levelG=+1
            //dbHandler.updateMitarbeiter(loggedmitarbeiter)
            returntomenue()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun returntomenue()
    {
        Toast.makeText(this, "Danke fürs Abstimmen", Toast.LENGTH_LONG).show()
        binding.confirmPress.text="Danke fürs Abstimmen"
        val handler = Handler()
        handler.postDelayed({
            //The code you want to run after the time is up
            //val intent = Intent(this, Menue::class.java)
            //startActivity(intent)
            finish()
        }, 2000) //the time you want to delay in milliseconds

    }
}