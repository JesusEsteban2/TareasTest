package com.example.tareastest.activities.data

import android.content.Context

class Tarea (
    val id:Int
    val task:String
    val doit:Boolean
    val cat:String
    ) {

}

class DaoTask (context: Context){
    private val databaseManager:DatabaseHelper=DatabaseHelper(context)
    var db=databaseManager.writableDatabase

    fun insert(task:Tarea){
        var d
        var ok=db.inser(db,task)
    }

    fun delete(i):Boolean{

    }

    fun update(ta:Tarea):Boolean{

    }

    fun query(criterio):List<Tarea>{

    }
}