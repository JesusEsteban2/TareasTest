package com.example.tareastest.activities.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper (context: Context)
    : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES_TAREAS)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    fun destroyDatabase(db: SQLiteDatabase?){
        db?.execSQL(SQL_DELETE_TABLE_TAREAS)
    }

    fun insertTareas(datab:SQLiteOpenHelper,t:Tarea):Boolean{

        // Create a new map of values, where column names are the key
        var values=ContentValues()

        values.put(SQL_TAREAS_COLUMS[0],t.task)
        values.put(SQL_TAREAS_COLUMS[1],t.doit)
        values.put(SQL_TAREAS_COLUMS[2],t.cat)

        // Insert the new row, returning the primary key value of the new row
        var db=datab.writableDatabase
        val newRowId = db.insert("Tareas", null, values)
        db.close()
        return (newRowId>0)
    }



    fun deleteTask(db:SQLiteDatabase,k:Int){

        // Delete row by id
        val rowDel = db.delete("Tareas", "ID=$k",null)
        Log.i("DB","Eliminado $rowDel registro")
    }

    fun searchTask(db:SQLiteDatabase,q:String):List<Tarea>{

        // Search by id
        val av=arrayOf<String>(q)

        val cursor:Cursor = db.query("Tareas",null,"ID LIKE ?",av,null,null,null)

        // Log.i("DB","Obtenidos ${cursor.count} registros")
        var lt:MutableList<Tarea> = mutableListOf<Tarea>()

        while (cursor.moveToNext()) {
            var t=Tarea(cursor.getInt(0),cursor.getString(1),
                cursor.getInt(2)==1,cursor.getString(3))
            lt.add(t)
        }
        return lt
    }

    companion object {
        const val DATABASE_NAME="tareastest.db"
        const val DATABASE_VERSION=1
        const val SQL_DELETE_TABLE_TAREAS="DROP TABLE IF EXISTS Tareas"
        const val SQL_CREATE_ENTRIES_TAREAS ="CREATE TABLE Tareas (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Task TEXT," +
                    "Doit BOOLEAN,"+
                    "Cat TEXT)"
        val SQL_TAREAS_COLUMS:List<String> = listOf("Task","Doit","Cat")
    }
}