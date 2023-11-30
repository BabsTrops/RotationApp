package com.example.rotations_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.Toast
import com.example.rotations_app.DBHelper.DBHelper
import com.example.rotations_app.Mitarbeiter.Mitarbeiter
import com.example.rotations_app.databinding.ActivityEvaluationBinding
import kotlinx.android.synthetic.main.activity_evaluation.*

class Evaluation : AppCompatActivity() {

    private lateinit var binding:ActivityEvaluationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)
        binding = ActivityEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Actionbar
        val actionbar = supportActionBar
        actionbar!!.title= "Bitte Bewerten:"
        actionbar.setDisplayHomeAsUpEnabled(true)

        //receive data
        var intentget= intent
        val mitarbeiterid = intent.getStringExtra("id");



        var  beanspruckungK:Int =0
        var  beanspruckungG:Int =0

        binding.btnEvaluation.setOnClickListener()
        {


            val handler = DBHelper(this)
            var emp: Mitarbeiter = handler.viewsingleMitarbeiter(mitarbeiterid.toString())

            var changeK:Int=emp.levelK-sbKoerperlich.progress
            var changeG:Int=emp.levelG-sbGeistig.progress

            if(changeK>25){changeK=25}
            if(changeK<0){changeK=0}
            if(changeG>25){changeG=25}
            if(changeG<0){changeG=0}

            handler.updateMitarbeiter(Mitarbeiter(emp.id, emp.name.toString(), emp.password.toString(), changeK, changeG))

            returntomenue()

        }

        val sbK = findViewById<SeekBar>(R.id.sbKoerperlich)
        val sbG = findViewById<SeekBar>(R.id.sbGeistig)


        sbKoerperlich?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser:Boolean){

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        sbGeistig?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser:Boolean){

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun returntomenue( )
    {
        //Toast.makeText(this, "Danke fürs Abstimmen", Toast.LENGTH_LONG).show()
        binding.tvConfirmbtn.text="Danke fürs Abstimmen"
        val handler = Handler()
        handler.postDelayed({
            finish()
        }, 1500) //the time you want to delay in milliseconds

    }
}