package com.example.rotations_app

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.rotations_app.DBHelper.DBHelper
import com.example.rotations_app.databinding.ActivityBewertungenBinding
import java.util.*


class Bewertungen : Base() {
    private lateinit var binding: ActivityBewertungenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBewertungenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dbHandler = DBHelper(this)

        //Actionbar
        val actionbar = supportActionBar
        actionbar!!.title= ""
        actionbar.setDisplayHomeAsUpEnabled(true)

        var intentget= intent
        val mitarbeiterid = intent.getStringExtra("id");
        var emp = dbHandler.viewsingleMitarbeiter(mitarbeiterid.toString())
        binding.K.text = emp.levelK.toString()
        binding.G.text = emp.levelG.toString()

        binding.progressBarG.progress = emp.levelG
        binding.progressBarK.progress = emp.levelK
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }


}