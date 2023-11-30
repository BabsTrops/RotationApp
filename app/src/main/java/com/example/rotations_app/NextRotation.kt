package com.example.rotations_app

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.rotations_app.databinding.ActivityNextrotationBinding
import java.text.SimpleDateFormat
import java.util.*

class NextRotation : AppCompatActivity() {
    private lateinit var binding: ActivityNextrotationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextrotationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Ihre nächste Rotation:"
        actionbar.setDisplayHomeAsUpEnabled(true)

        var currentDay: String = dayofweek()
        var workplace: String = rotation(currentDay)


        println("Tag ist: $currentDay")
        println("Arbeitsplatz ist: $workplace")
        binding.weekday.text= currentDay.toString()
        binding.workplace.text= workplace
        binding.duration.text= "07:15 - 15:15 Uhr "

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun rotation(wochentag: String):String {
        var Tag: String = " "
        when (wochentag) {
            "Montag" -> Tag= "Maschine: A"
            "Dienstag" -> Tag="Maschine: B"
            "Mittwoch" -> Tag= "Maschine: C"
            "Donnerstag" -> Tag= "Maschine: D"
            "Freitag" -> Tag= "Maschine: E"
            "Samstag", "Sonntag" ->Tag="Sie haben frei"
        }
        return Tag
    }

    fun dayofweek(): String {
        var Wochentag: String = " "
        var C = Calendar.getInstance();
        //C.setTime(yourdate); // yourdate is an object of type Date

        when (C.get(Calendar.DAY_OF_WEEK)-1) {
            1 -> Wochentag= "Montag"
            2 -> Wochentag="Dienstag"
            3 -> Wochentag= "Mittwoch"
            4 -> Wochentag= "Donnerstag"
            5 -> Wochentag= "Freitag"
            6, 7 ->Wochentag="Sie haben frei"
        }
        return Wochentag

    }

    /*fun clickDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedyear, selectedmonth, selecteddayOfMonth ->
            Toast.makeText(this,"The chosen year is "+ year, Toast.LENGTH_LONG).show()

            val selectedDate = "$selecteddayOfMonth/ ${selectedmonth+1}/$selectedyear"

            findViewById<TextView>(R.id.tvSelectedDate).setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate=sdf.parse(selectedDate)
        }
            ,year
            ,month
            ,day
        ).show()*/
}