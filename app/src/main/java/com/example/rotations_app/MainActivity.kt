package com.example.rotations_app

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.Toast
import com.example.rotations_app.DBHelper.DBHelper
import com.example.rotations_app.Mitarbeiter.Mitarbeiter
import com.example.rotations_app.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Base() {
    private lateinit var binding: ActivityMainBinding

    var idn: Int = 0
    var namen: String? = null
    var passwordn: String? = null
    var levelkn: Int = 0
    var levelgn: Int = 0
    //initialize object of Mitarbeiter
    var loggedmitarbeiter = Mitarbeiter(0, "", "", 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(findViewById(R.id.toolbar))
        var dbHandler = DBHelper(this)

        //As soon as LogIn Button is pressed
        binding.LogIn.setOnClickListener {

            //*******************************************************************************************
            //Fill Mitarbeiter Object with Data
            loggedmitarbeiter = dbHandler.viewsingleMitarbeiter(binding.MitarbeiterID.text.toString())

            //Compare LogIn Data with Database or Developer access

            if (binding.MitarbeiterID.text.toString() == "developer" && binding.Passwort.text.toString() == "password") {
                //If Data is correct --> Open Menue Activity
                val intent = Intent(this, Menue::class.java)
                //Pass Mitarbeiter Data to Menue Activity
                intent.putExtra("loggedmitarbeiter", loggedmitarbeiter.id);
                startActivity(intent)
                //finish()
            } else if (binding.Passwort.text.toString() == loggedmitarbeiter.password) {
                val intent = Intent(this, Menue::class.java)
                //Pass Mitarbeiter Data to next Activity
                intent.putExtra("logmit", loggedmitarbeiter.id.toString());
                startActivity(intent)
                //finish()
            } else {
                Toast.makeText(this, "Benutzerdaten sind Falsch", Toast.LENGTH_SHORT).show()
            }

            //Log In without Data base --> Necessary if Database is not yet initialized
            //************************************************************************************************

            /*
            if ((binding.Passwort.text.toString().isEmpty()) || (binding.MitarbeiterID.text.toString().isEmpty())) {
               Toast.makeText(this, "Bitte Daten eingeben", Toast.LENGTH_SHORT).show()
            }
            else if(binding.Passwort.text.toString()=="t"&& binding.MitarbeiterID.text.toString()=="n"){
               val intent = Intent(this, Menue::class.java)
              startActivity(intent)
               finish()
            }else{
               Toast.makeText(this, "Benutzerdaten sind Falsch", Toast.LENGTH_SHORT).show()

            }

             */

            //**********************************************************************************************
        }


    }



}