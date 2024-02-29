package com.example.tareastest.activities.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper (context: Context)
    : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    fun destroyDatabase(db: SQLiteDatabase?){
        db?.execSQL(SQL_DELETE_TABLE)
    }

    fun insertTask(db:SQLiteDatabase,v: List<Any>){

        // Create a new map of values, where column names are the keys
        var values=ContentValues()
        var d=0
        for (c in SQL_TAREAS_COLUMS){
            when {
                v[d] is String -> values.put(c,v[d] as String)
                v[d] is Int -> values.put(c,v[d] as Int)
                v[d] is Boolean -> values.put(c,v[d] as Boolean)
                else -> values.put(c,v[d].toString())
            }

            d++
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert("Tareas", null, values)
        Log.i("DB","Insertado registro id: $newRowId")
    }


    companion object {
        const val DATABASE_NAME="tareastest.db"
        const val DATABASE_VERSION=1
        const val SQL_DELETE_TABLE="DROP TABLE IF EXISTS Tareas"
        const val SQL_CREATE_ENTRIES ="CREATE TABLE Tareas (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Task TEXT," +
                    "Doit BOOLEAN,"+
                    "Cat TEXT)"
        val SQL_TAREAS_COLUMS:List<String> = listOf("Task","Doit","Cat")
    }
}