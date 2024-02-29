package com.example.tareastest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tareastest.R
import com.example.tareastest.activities.data.DatabaseHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db= DatabaseHelper(this)
        var v: List<Any> = listOf("Ir a clase",false,"Trabajo")
        db.insertTask(db.writableDatabase,v)
        var v2: List<Any> = listOf("Hacer la compra",false,"Personal")
        db.insertTask(db.writableDatabase,v2)
    }
}