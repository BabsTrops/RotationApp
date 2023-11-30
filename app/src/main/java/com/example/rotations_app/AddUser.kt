package com.example.rotations_app

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rotations_app.DBHelper.DBHelper
import com.example.rotations_app.Mitarbeiter.Mitarbeiter
import kotlinx.android.synthetic.main.content.*
import kotlinx.android.synthetic.main.dialog_update.*

class AddUser : Base() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        //Actionbar
        val actionbar = supportActionBar
        actionbar!!.title= "Neuer Mitarbeiter:"
        actionbar.setDisplayHomeAsUpEnabled(true)


        btnAdd.setOnClickListener { view ->

            addRecord(view)
        }

        setupListofDataIntoRecyclerView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * Function is used show the list of inserted data.
     */
    fun setupListofDataIntoRecyclerView() {

        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE

            // Set the LayoutManager that this RecyclerView will use.
            rvItemsList.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = Adapters(this, getItemsList())
            // adapter instance is set to the recyclerview to inflate the items.
            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }

    /**
     * Function is used to get the Items List which is added in the database table.
     */
    private fun getItemsList(): ArrayList<Mitarbeiter> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DBHelper = DBHelper(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val empList: ArrayList<Mitarbeiter> = databaseHandler.viewMitarbeiter()

        return empList
    }

    //method for saving records in database
    fun addRecord(view: View) {
        val name = etName.text.toString()
        val password = etPassword.text.toString()
        val lk = 25
        val lg = 25

        var dbHandler= DBHelper(this)
        //val databaseHandler: DBHelper = DBHelper(this)

        if (!name.isEmpty() && !password.isEmpty()) {
            val status = dbHandler.addMitarbeiter(Mitarbeiter(0, name, password,lk, lg))

            if (status > -1) {
                Toast.makeText(applicationContext, "User saved", Toast.LENGTH_LONG).show()
                etName.text.clear()
                etPassword.text.clear()

                setupListofDataIntoRecyclerView()
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    "Name or Password cant be blank",
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Method is used to show the Custom Dialog.
     */

    fun updateRecordDialog(mitarbeiter: Mitarbeiter) {
        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        updateDialog.setContentView(R.layout.dialog_update)

        updateDialog.etUpdateName.setText(mitarbeiter.name)
        updateDialog.etUpdatePassword.setText(mitarbeiter.password)

        updateDialog.tvUpdate.setOnClickListener(View.OnClickListener {

            val name = updateDialog.etUpdateName.text.toString()
            val password = updateDialog.etUpdatePassword.text.toString()

            val databaseHandler: DBHelper = DBHelper(this)

            if (!name.isEmpty() && !password.isEmpty()) {
                val status =
                        databaseHandler.updateMitarbeiter(Mitarbeiter(mitarbeiter.id, name, password,mitarbeiter.levelK ,mitarbeiter.levelG ))
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record Updated.", Toast.LENGTH_LONG).show()

                    setupListofDataIntoRecyclerView()

                    updateDialog.dismiss() // Dialog will be dismissed
                }
            } else {
                Toast.makeText(
                        applicationContext,
                        "Name or Email cannot be blank",
                        Toast.LENGTH_LONG
                ).show()
            }
        })
        updateDialog.tvCancel.setOnClickListener(View.OnClickListener {
            updateDialog.dismiss()
        })
        //Start the dialog and display it on screen.
        updateDialog.show()
    }

    /**
     * Method is used to show the Alert Dialog.
     */
    fun deleteRecordAlertDialog(mitarbeiter: Mitarbeiter) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Record")
        //set message for alert dialog
        builder.setMessage("Soll ${mitarbeiter.name} gelöscht werden?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->

            //creating the instance of DatabaseHandler class
            val databaseHandler: DBHelper = DBHelper(this)
            //calling the deleteEmployee method of DatabaseHandler class to delete record
            val status = databaseHandler.deleteEmployee(Mitarbeiter(mitarbeiter.id, "", "", 0, 0))
            if (status > -1) {
                Toast.makeText(
                        applicationContext,
                        "Record deleted successfully.",
                        Toast.LENGTH_LONG
                ).show()

                setupListofDataIntoRecyclerView()
            }

            dialogInterface.dismiss() // Dialog will be dismissed
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }


}
