package com.example.tareastest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tareastest.R
import com.example.tareastest.activities.data.DatabaseHelper
import com.example.tareastest.activities.data.Tarea
import com.example.tareastest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db= DatabaseHelper(this)


        //var v: List<Any> = listOf("Ir a clase",false,"Trabajo")
        //db.insertTask(db.writableDatabase,v)
        //var v2: List<Any> = listOf("Hacer la compra",false,"Personal")
        //db.insertTask(db.writableDatabase,v2)
        //var v3: List<Any> = listOf("Estudiar en casa",false,"Trabajo")
        //db.insertTask(db.writableDatabase,v2)
        //db.deleteTask(db.writableDatabase,1)
        //db.deleteTask(db.writableDatabase,1)
        var ls:MutableList<Tarea> = db.searchTask(db.writableDatabase,"%")
        for (task<Tarea> in ls) {
            var t =
            t=t+
        }
    }
}