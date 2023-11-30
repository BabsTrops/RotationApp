package com.example.rotations_app

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.rotations_app.databinding.ActivityImmediaterotationBinding
import java.util.*
import android.content.Intent
import android.os.Handler
import android.widget.Toast

class Immediaterotation : AppCompatActivity() {
    private lateinit var binding: ActivityImmediaterotationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImmediaterotationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Actionbar
       val actionbar = supportActionBar
       actionbar!!.title= "Bitte Abstimmen:"
       actionbar.setDisplayHomeAsUpEnabled(true)

        binding.btnhelpK.setOnClickListener(){
            returntomenue()
        }

        binding.btnhelpG.setOnClickListener(){
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
        binding.tvhelp.text="Hilfe ist unterwegs"
        val handler = Handler()
        handler.postDelayed({
            finish()
        }, 1500) //the time you want to delay in milliseconds

    }
}
