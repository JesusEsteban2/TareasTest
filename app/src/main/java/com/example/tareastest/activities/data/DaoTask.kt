package com.example.tareastest.activities.data

import android.content.Context


class Tarea (
    val id:Int,
    var task:String,
    var doit:Boolean,
    var cat:Int
    ) {

}

class DaoTask (context: Context){
    val databaseManager:DatabaseHelper=DatabaseHelper(context)


    fun insert(task:Tarea):Boolean {

        var ok=databaseManager.insertTask(task)
        return ok

    }

    fun delete(i:Int):Boolean{

        var ok=databaseManager.deleteTask(i)
        return ok
    }

    fun update(ta:Tarea):Boolean{

        var ok=databaseManager.updateTask(ta)
        return ok
    }

    fun queryById(i:Int): Tarea? {

        var t:Tarea?=databaseManager.searchById(i)
        return t
    }

    fun queryByUndo():List<Tarea>{

        var lt :List<Tarea> =databaseManager.searchByUndo()
        return lt

    }

    fun queryAll():List<Tarea>{
        var lt :List<Tarea> =databaseManager.searchAll()
        return lt
    }
}