package com.example.tareastest.activities.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DatabaseHelper (context: Context)
    : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db.execSQL(SQL_CREATE_ENTRIES)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db: SQLiteDatabase?)
        onCreate(db: SQLiteDatabase?)
    }

    fun destroyDatabase(db: SQLiteDatabase?){
        db.execSQL(SQL_DELETE_TABLE)
    }

    fun createTask(db:SQLiteDatabase){

    }
    companion object {
        const val DATABASE_NAME="tareastest.db"
        const val DATABASE_VERSION=1
        const val SQL_DELETE_TABLE="DROP TABLE IF EXISTS Tareas"
        const val SQL_CREATE_ENTRIES ="CREATE TABLE Tareas (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Task TEXT," +
                    "Do BOOLEAN),"+
                    "Cat TEXT"
        const val SQL_INSERT_TASK ="INSERT INTO Tareas  "

        TODO("A implementar")

        //https://developer.android.com/training/data-storage/sqlite?hl=es-419
        //https://developer.android.com/jetpack/androidx/releases/sqlite?hl=es-419
        //https://cursokotlin.com/curso-programacion-kotlin-android/
        //https://www.w3schools.com/sql/default.asp
        //https://www.sqlite.org/docs.html



        }
    }


}