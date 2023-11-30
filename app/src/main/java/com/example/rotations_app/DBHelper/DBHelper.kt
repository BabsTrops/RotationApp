package com.example.rotations_app.DBHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.widget.AppCompatEditText
import com.example.rotations_app.Mitarbeiter.Mitarbeiter

class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VER) {

    //companion= private val
    companion object{
        private val DATABASE_VER = 1
        private val DATABASE_NAME= "Mitarbeiter.db"

        //Table
        private val TABLE_NAME="Mitarbeiter_Table"
        private val ID="MitarbeiterID"
        private val NAME="Name"
        private val PASSWORD="Passwort"
        private val LEVELK="LevelK"
        private val LEVELG="LevelG"
    }

    //create the table
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_MITARBEITER_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT,"
                + PASSWORD + " TEXT,"
                + LEVELK + " INTEGER,"
                + LEVELG + " INTEGER) ")
        db?.execSQL(CREATE_MITARBEITER_TABLE);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME )
        onCreate(db)
    }

    fun addMitarbeiter(mitarbeiter: Mitarbeiter): Long{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(NAME, mitarbeiter.name)
        contentValues.put(PASSWORD, mitarbeiter.password)
        contentValues.put(LEVELK, mitarbeiter.levelK)
        contentValues.put(LEVELG, mitarbeiter.levelG)

        //Inserting Row
        val success = db.insert(TABLE_NAME, null, contentValues)

        db.close()
        return success
    }

    @SuppressLint("Range")
    fun viewsingleMitarbeiter(mitarbeiter: String): Mitarbeiter{

        val db= this.readableDatabase
        val cursor = db.rawQuery("SELECT* FROM $TABLE_NAME", null)
        var id:Int =0
        var name:String = ""
        var password: String = ""
        var levelk:Int=0
        var levelg:Int=0

        while (cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndex(ID))==mitarbeiter)
            {
                id = (cursor.getInt(cursor.getColumnIndex(ID)))
                name = (cursor.getString(cursor.getColumnIndex(NAME)))
                password = (cursor.getString(cursor.getColumnIndex(PASSWORD)))
                levelk = (cursor.getInt(cursor.getColumnIndex(LEVELK)))
                levelg = (cursor.getInt(cursor.getColumnIndex(LEVELG)))

            }

        }
        cursor.close()
       return  Mitarbeiter(id,name,password,levelk,levelg)
    }


    @SuppressLint("Range")
    fun viewMitarbeiter(): ArrayList<Mitarbeiter>{

        val mitarbeiterList: ArrayList<Mitarbeiter> = ArrayList<Mitarbeiter>()

        val selectQuery = "SELECT * FROM $TABLE_NAME"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch(e: SQLiteException){
            db.execSQL(selectQuery)
            return  ArrayList()
        }
        var id: Int
        var name: String
        var password: String
        var levelk: Int
        var levelg: Int

        if(cursor.moveToFirst())
        {
             do{
                id = cursor.getInt(cursor.getColumnIndex(ID))
                name = cursor.getString(cursor.getColumnIndex(NAME))
                password = cursor.getString(cursor.getColumnIndex(PASSWORD))
                levelk = cursor.getInt(cursor.getColumnIndex(LEVELK))
                levelg = cursor.getInt(cursor.getColumnIndex(LEVELG))

                val mitarbeiter = Mitarbeiter(id=id, name=name, password=password, levelK=levelk, levelG=levelg)
                mitarbeiterList.add(mitarbeiter)
            }while(cursor.moveToNext())
        }
        return mitarbeiterList
    }

    @SuppressLint("Range")
    fun get_password(id: String): String {
        val db= this.readableDatabase
        val cursor = db.rawQuery("SELECT* FROM $TABLE_NAME", null)
        var password: String = "Empty"

        while (cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndex(ID))==id)
            {
                password = (cursor.getString(cursor.getColumnIndex(PASSWORD)))
            }
        }
        cursor.close()
        return password
    }

    fun updateMitarbeiter(mitarbeiter: Mitarbeiter): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, mitarbeiter.name)
        contentValues.put(PASSWORD, mitarbeiter.password)
        contentValues.put(LEVELK, mitarbeiter.levelK)
        contentValues.put(LEVELG, mitarbeiter.levelG)
        // Updating Row
        val success = db.update(TABLE_NAME, contentValues, ID + "=" + mitarbeiter.id, null)
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection
        return success
    }

    fun deleteEmployee(mitarbeiter: Mitarbeiter): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, mitarbeiter.id) // EmpModelClass id
        // Deleting Row
        val success = db.delete(TABLE_NAME, ID + "=" + mitarbeiter.id, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

}